package com.unear.pos.notification.publisher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unear.pos.notification.dto.PosNotificationEventRequest;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationPublisher {

    private final ObjectMapper objectMapper;

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String NOTIFICATION_STREAM = "pos-notification-stream";

    public void publishNotification(PosNotificationEventRequest request) {
        try {
            StreamOperations<String, Object, Object> streamOps = redisTemplate.opsForStream();

            Map<String, String> map = objectMapper.convertValue(request, new TypeReference<>() {
            });
            MapRecord<String, String, String> record = StreamRecords.newRecord()
                    .in(NOTIFICATION_STREAM)
                    .ofMap(map);

            streamOps.add(record);

            log.info("알림 발행 성공 (Redis Stream) - type: {}, userId: {}", request.getType(), request.getUserId());
        } catch (Exception e) {
            log.error("알림 발행 실패 (Redis Stream) - type: {}, userId: {}, reason: {}",
                    request.getType(), request.getUserId(), e.getMessage(), e);
        }
    }
}
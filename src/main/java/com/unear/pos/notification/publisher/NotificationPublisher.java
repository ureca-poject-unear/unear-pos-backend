package com.unear.pos.notification.publisher;

import com.unear.pos.notification.dto.PosNotificationEventRequest;
import java.util.HashMap;
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

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String NOTIFICATION_STREAM = "pos-notification-stream";

    public void publishNotification(PosNotificationEventRequest request) {
        try {
            StreamOperations<String, Object, Object> streamOps = redisTemplate.opsForStream();

            Map<String, String> map = createMapFromRequest(request);

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

    private Map<String, String> createMapFromRequest(PosNotificationEventRequest request) {
        Map<String, String> map = new HashMap<>();

        map.put("userId", String.valueOf(request.getUserId()));
        map.put("stampOrder", String.valueOf(request.getStampOrder()));
        map.put("type", request.getType().toString());
        map.put("message", request.getMessage() != null ? request.getMessage() : "");

        if (request.getRelatedPlaceId() != null) {
            map.put("relatedPlaceId", String.valueOf(request.getRelatedPlaceId()));
        }
        if (request.getRelatedPlaceName() != null) {
            map.put("relatedPlaceName", request.getRelatedPlaceName());
        }
        if (request.getRelatedEventId() != null) {
            map.put("relatedEventId", String.valueOf(request.getRelatedEventId()));
        }
        if (request.getDiscountAmount() != null) {
            map.put("discountAmount", String.valueOf(request.getDiscountAmount()));
        }
        if (request.getFinalAmount() != null) {
            map.put("finalAmount", String.valueOf(request.getFinalAmount()));
        }

        return map;
    }
}
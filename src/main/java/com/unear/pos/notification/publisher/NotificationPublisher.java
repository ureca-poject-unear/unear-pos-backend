package com.unear.pos.notification.publisher;

import com.unear.pos.notification.dto.PosNotificationEventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationPublisher {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String NOTIFICATION_CHANNEL = "pos_notification_channel";

    public void publishNotification(PosNotificationEventRequest request) {
        try {
            redisTemplate.convertAndSend(NOTIFICATION_CHANNEL, request);
            log.info("알림 발행 성공 - type: {}, userId: {}", request.getType(), request.getUserId());
        } catch (Exception e) {
            log.error("알림 발행 실패 - type: {}, userId: {}, reason: {}",
                    request.getType(), request.getUserId(), e.getMessage(), e);
        }
    }
}

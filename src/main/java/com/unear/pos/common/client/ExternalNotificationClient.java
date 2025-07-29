package com.unear.pos.common.client;

import com.unear.pos.stamp.dto.StampNotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExternalNotificationClient {

    private final RestTemplate restTemplate;

    @Value("${notification.server.url:http://localhost:9090}")
    private String notificationServerUrl;

    public void sendStampNotification(StampNotificationDto notification) {
        try {
            String url = notificationServerUrl + "/api/notifications/send";
            restTemplate.postForObject(url, notification, Void.class);
            log.info("Stamp notification sent to external server for user: {}", notification.getUserId());
        } catch (Exception e) {
            log.error("Failed to send stamp notification to external server for user: {}",
                    notification.getUserId(), e);
        }
    }
}
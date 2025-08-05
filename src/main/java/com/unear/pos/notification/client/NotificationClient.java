package com.unear.pos.notification.client;

import com.unear.pos.notification.dto.PosNotificationEventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationClient {
    private final RestTemplate restTemplate;
    private final String USER_SERVICE_URL = "http://user-service/internal/pos/notify";
    private static final String INTERNAL_KEY_HEADER = "INTERNAL_KEY";

    @Value("${internal.key}")
    private String internalKeyValue;


    @Value("${internal.user-service-url}")
    private String userServiceUrl;


    public void sendNotification(PosNotificationEventRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set(INTERNAL_KEY_HEADER, internalKeyValue);

            HttpEntity<PosNotificationEventRequest> entity = new HttpEntity<>(request, headers);
            restTemplate.postForEntity(userServiceUrl, entity, Void.class);

            log.info("알림 전송 성공 - type: {}, userId: {}", request.getType(), request.getUserId());
        } catch (Exception e) {
            log.error("알림 전송 실패 - type: {}, userId: {}, reason: {}",
                    request.getType(), request.getUserId(), e.getMessage(), e);
        }
    }
}
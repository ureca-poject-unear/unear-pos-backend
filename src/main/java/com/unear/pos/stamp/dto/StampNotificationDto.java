package com.unear.pos.stamp.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class StampNotificationDto {
    private Long userId;
    private Long stampId;
    private String eventName;
    private String placeName;
    private String eventCode;
    private LocalDateTime stampedAt;
    private String message;
    private String notificationType;
}
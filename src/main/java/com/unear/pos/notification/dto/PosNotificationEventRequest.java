package com.unear.pos.notification.dto;

import com.unear.pos.common.dto.enums.PosNotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PosNotificationEventRequest {
    private Long userId;
    private int stampOrder;
    private PosNotificationType type;
    private String message;
    private Long relatedPlaceId;
    private String relatedPlaceName;
    private Long relatedEventId;
    private Long discountAmount;
    private Long finalAmount;

    public static PosNotificationEventRequest fromStampAdded(Long userId,
                                                             Long relatedPlaceId,
                                                             String relatedPlaceName,
                                                             String message,
                                                             int stampOrder) {
        return PosNotificationEventRequest.builder()
                .userId(userId)
                .type(PosNotificationType.STAMP_ADDED)
                .message(message)
                .relatedPlaceId(relatedPlaceId)
                .relatedPlaceName(relatedPlaceName)
                .stampOrder(stampOrder)
                .build();
    }

    public static PosNotificationEventRequest fromStampCompleted(Long userId,
                                                                 Long relatedPlaceId,
                                                                 String relatedPlaceName,
                                                                 String message) {
        return PosNotificationEventRequest.builder()
                .userId(userId)
                .type(PosNotificationType.STAMP_COMPLETED)
                .message(message)
                .relatedPlaceId(relatedPlaceId)
                .relatedPlaceName(relatedPlaceName)
                .build();
    }

    public static PosNotificationEventRequest fromPaymentSuccess(Long userId,
                                                                 Long relatedPlaceId,
                                                                 String relatedPlaceName,
                                                                 String message,
                                                                 Long discountAmount,
                                                                 Long finalAmount) {
        return PosNotificationEventRequest.builder()
                .userId(userId)
                .type(PosNotificationType.PAYMENT_SUCCESS)
                .message(message)
                .relatedPlaceId(relatedPlaceId)
                .relatedPlaceName(relatedPlaceName)
                .discountAmount(discountAmount)
                .finalAmount(finalAmount)
                .build();
    }
}

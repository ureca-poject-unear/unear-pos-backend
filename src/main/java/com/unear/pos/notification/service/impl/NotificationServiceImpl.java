package com.unear.pos.notification.service.impl;

import com.unear.pos.common.dto.MemberSession;
import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.notification.dto.PosNotificationEventRequest;
import com.unear.pos.notification.publisher.NotificationPublisher;
import com.unear.pos.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationPublisher notificationPublisher;

    @Override
    public void sendPaymentSuccessNotification(MemberSession session, PosSessionInfo posInfo, Long paymentAmount) {
        PosNotificationEventRequest request = PosNotificationEventRequest.fromPaymentSuccess(
                session.getMemberId(),
                posInfo.getPlaceId(),
                posInfo.getPlaceName(),
                String.format(" %s 결제가 완료되었습니다", posInfo.getPlaceName()),
                session.getTotalDiscountAmount(),
                paymentAmount
        );
        notificationPublisher.publishNotification(request);

    }

    @Override
    public void sendStampCompletedNotification(Long userId, Long placeId, String placeName, String eventCode,
                                               String eventName) {
        PosNotificationEventRequest request = PosNotificationEventRequest.fromStampCompleted(
                userId,
                placeId,
                placeName,
                String.format("스탬프를 모두 모았습니다", placeName)
        );
        notificationPublisher.publishNotification(request);

    }

    @Override
    public void sendStampAddedNotification(Long userId, Long placeId, String placeName, int currentStampCount,
                                           int requiredStampCount) {
        PosNotificationEventRequest request = PosNotificationEventRequest.fromStampAdded(
                userId,
                placeId,
                placeName,
                String.format(" %s에서 스탬프를 획득했습니다! (%d/%d)", placeName, currentStampCount, requiredStampCount),
                requiredStampCount
        );
        notificationPublisher.publishNotification(request);

    }


}

package com.unear.pos.notification.service;

import com.unear.pos.common.dto.MemberSession;
import com.unear.pos.common.dto.PosSessionInfo;

public interface NotificationService {
    void sendPaymentSuccessNotification(MemberSession session, PosSessionInfo posInfo, Long paymentAmount);

    void sendStampCompletedNotification(Long userId, Long placeId, String placeName, String eventCode,
                                        String eventName);

    void sendStampAddedNotification(Long userId, Long placeId, String placeName, int currentStampCount,
                                    int requiredStampCount);
}
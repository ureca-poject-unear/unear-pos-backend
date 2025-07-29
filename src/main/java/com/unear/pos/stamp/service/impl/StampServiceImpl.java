package com.unear.pos.stamp.service.impl;

import com.unear.pos.common.client.ExternalNotificationClient;
import com.unear.pos.common.dto.MemberSession;
import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.dto.enums.EventParticipationStatus;
import com.unear.pos.stamp.dto.StampNotificationDto;
import com.unear.pos.stamp.entity.EventPlace;
import com.unear.pos.stamp.entity.Stamp;
import com.unear.pos.stamp.entity.UnearEvent;
import com.unear.pos.stamp.repository.EventPlaceRepository;
import com.unear.pos.stamp.repository.StampRepository;
import com.unear.pos.stamp.repository.UnearEventRepository;
import com.unear.pos.stamp.service.StampService;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StampServiceImpl implements StampService {

    private final StampRepository stampRepository;
    private final EventPlaceRepository eventPlaceRepository;
    private final UnearEventRepository unearEventRepository;
    private final ExternalNotificationClient notificationClient;

    @Override
    @Transactional
    public void createStampAfterPayment(MemberSession memberSession, PosSessionInfo posInfo) {
        if (posInfo.getEventStatus() == EventParticipationStatus.NONE) {
            log.debug("Place {} is not participating in events", posInfo.getPlaceId());
            return;
        }

        EventPlace eventPlace = eventPlaceRepository.findByPlaceId(posInfo.getPlaceId())
                .orElseThrow(() -> new IllegalStateException("이벤트 매장 정보를 찾을 수 없습니다"));

        if (stampRepository.existsByUserIdAndEventPlaceId(memberSession.getMemberId(), eventPlace.getEventPlaceId())) {
            log.info("Stamp already exists for user {} at event place {}",
                    memberSession.getMemberId(), eventPlace.getEventPlaceId());
            return;
        }

        UnearEvent activeEvent = unearEventRepository.findActiveEvent()
                .orElseThrow(() -> new IllegalStateException("진행중인 이벤트를 찾을 수 없습니다"));

        Stamp stamp = Stamp.builder()
                .userId(memberSession.getMemberId())
                .eventPlaceId(eventPlace.getEventPlaceId())
                .stampedAt(LocalDateTime.now())
                .eventCode(posInfo.getEventStatus().getCode())
                .placeName(posInfo.getPlaceName())
                .build();

        Stamp savedStamp = stampRepository.save(stamp);
        log.info("Stamp created: {}", savedStamp.getStampId());

        sendStampNotification(savedStamp, activeEvent);
    }

    private void sendStampNotification(Stamp stamp, UnearEvent event) {
        StampNotificationDto notification = StampNotificationDto.builder()
                .userId(stamp.getUserId())
                .stampId(stamp.getStampId())
                .eventName(event.getEventName())
                .placeName(stamp.getPlaceName())
                .eventCode(stamp.getEventCode())
                .stampedAt(stamp.getStampedAt())
                .message(String.format("🎉 %s에서 스탬프를 획득했습니다!", stamp.getPlaceName()))
                .notificationType("STAMP_CREATED")
                .build();

        CompletableFuture.runAsync(() -> notificationClient.sendStampNotification(notification));
    }
}

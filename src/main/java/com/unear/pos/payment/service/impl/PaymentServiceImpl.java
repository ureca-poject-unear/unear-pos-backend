package com.unear.pos.payment.service.impl;

import com.unear.pos.common.dto.MemberSession;
import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.util.MemberSessionUtil;
import com.unear.pos.payment.dto.request.PaymentRequestDto;
import com.unear.pos.payment.dto.response.PaymentResponseDto;
import com.unear.pos.payment.entity.UserHistory;
import com.unear.pos.payment.repository.UserHistoryRepository;
import com.unear.pos.payment.service.PaymentService;
import com.unear.pos.stamp.service.StampService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final StampService stampService;
    private final UserHistoryRepository userHistoryRepository;
    private final MemberSessionUtil memberSessionUtil;

    @Override
    public PaymentResponseDto processPayment(PaymentRequestDto request, HttpSession session, PosSessionInfo posInfo) {
        MemberSession memberSession = memberSessionUtil.validateAndGetMemberSession(session);

        validatePaymentAmount(request.getPaymentAmount(), memberSession);

        UserHistory userHistory = createUserHistory(memberSession, posInfo, request);
        UserHistory savedHistory = userHistoryRepository.save(userHistory);

        try {
            stampService.createStampAfterPayment(memberSession, posInfo);
        } catch (Exception e) {
            log.error("Failed to create stamp after payment", e);
        }

        memberSessionUtil.clearMemberSession(session);

        return createPaymentResponse(savedHistory);

    }

    private void validatePaymentAmount(Long paymentAmount, MemberSession memberSession) {
        if (memberSession.getOriginalAmount() == null) {
            throw new IllegalStateException("할인 적용 후 결제가 가능합니다");
        }

        Long expectedAmount = memberSession.getOriginalAmount() - memberSession.getTotalDiscountAmount();
        if (!paymentAmount.equals(expectedAmount)) {
            throw new IllegalArgumentException(
                    String.format("결제 금액이 일치하지 않습니다. 예상: %d, 요청: %d", expectedAmount, paymentAmount)
            );
        }
    }

    private UserHistory createUserHistory(MemberSession memberSession, PosSessionInfo posInfo,
                                          PaymentRequestDto request) {
        return UserHistory.builder()
                .userId(memberSession.getMemberId())
                .userCouponId(memberSession.getUserCouponId())
                .placeId(posInfo.getPlaceId())
                .usedAt(LocalDate.now())
                .originalAmount(memberSession.getOriginalAmount().intValue())
                .membershipDiscountAmount(getMembershipDiscountAmount(memberSession))
                .couponDiscountAmount(getCouponDiscountAmount(memberSession))
                .totalDiscountAmount(memberSession.getTotalDiscountAmount().intValue())
                .totalPaymentAmount(request.getPaymentAmount().intValue())
                .isMembershipUsed(memberSession.getMembershipDiscount() != null)
                .isCouponUsed(memberSession.getCouponDiscount() != null)
                .paidAt(LocalDate.now())
                .discountCode(memberSession.getDiscountCode())
                .membershipCode(memberSession.getMemberGrade().getCode())
                .placeCategory(posInfo.getPlaceCategory().getCode())
                .build();
    }

    private Integer getMembershipDiscountAmount(MemberSession memberSession) {
        return memberSession.getMembershipDiscount() != null
                ? memberSession.getMembershipDiscount().getDiscountAmount().intValue()
                : 0;
    }

    private Integer getCouponDiscountAmount(MemberSession memberSession) {
        return memberSession.getCouponDiscount() != null
                ? memberSession.getCouponDiscount().getDiscountAmount().intValue()
                : 0;
    }

    private PaymentResponseDto createPaymentResponse(UserHistory userHistory) {
        return PaymentResponseDto.builder()
                .userHistoryId(userHistory.getUserHistoryId())
                .originalAmount(userHistory.getOriginalAmount().longValue())
                .membershipDiscountAmount(userHistory.getMembershipDiscountAmount().longValue())
                .couponDiscountAmount(userHistory.getCouponDiscountAmount().longValue())
                .totalDiscountAmount(userHistory.getTotalDiscountAmount().longValue())
                .finalPaymentAmount(userHistory.getTotalPaymentAmount().longValue())
                .paidAt(userHistory.getPaidAt())
                .isMembershipUsed(userHistory.getIsMembershipUsed())
                .isCouponUsed(userHistory.getIsCouponUsed())
                .build();
    }


    @Override
    public List<UserHistory> getPlacePaymentHistory(Long placeId) {
        return userHistoryRepository.findByPlaceIdOrderByPaidAtDesc(placeId);

    }
}

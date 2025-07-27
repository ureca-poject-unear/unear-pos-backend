package com.unear.pos.payment.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PaymentResponseDto {
    private Long userHistoryId;
    private Long originalAmount;
    private Long membershipDiscountAmount;
    private Long couponDiscountAmount;
    private Long totalDiscountAmount;
    private Long finalPaymentAmount;
    private LocalDate paidAt;
    private Boolean isMembershipUsed;
    private Boolean isCouponUsed;
}
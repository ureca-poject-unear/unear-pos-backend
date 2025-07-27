package com.unear.pos.coupon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CouponVerifyResponseDto {
    private Long userCouponId;
    private String discountCode;
    private Long unitBaseAmount;
    private Long fixedDiscount;
    private Long discountPercent;
    private Long minPurchaseAmount;
    private Long maxDiscountAmount;
}
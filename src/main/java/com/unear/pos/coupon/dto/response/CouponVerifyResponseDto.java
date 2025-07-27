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
    private Integer unitBaseAmount;
    private Integer fixedDiscount;
    private Integer discountPercent;
    private Integer minPurchaseAmount;
    private Integer maxDiscountAmount;
}
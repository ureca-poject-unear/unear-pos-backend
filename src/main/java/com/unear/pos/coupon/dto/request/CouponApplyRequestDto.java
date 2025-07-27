package com.unear.pos.coupon.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CouponApplyRequestDto {
    @NotNull(message = "사용자 쿠폰 ID는 필수입니다")
    private Long userCouponId;

    @NotNull(message = "구매 금액은 필수입니다")
    @Min(value = 0, message = "구매 금액은 0 이상이어야 합니다")
    private Long purchaseAmount;
}
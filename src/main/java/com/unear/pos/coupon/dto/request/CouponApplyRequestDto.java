package com.unear.pos.coupon.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CouponApplyRequestDto {
    @NotNull(message = "사용자 쿠폰 ID는 필수입니다")
    private Long userCouponId;
}
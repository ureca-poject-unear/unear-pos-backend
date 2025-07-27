package com.unear.pos.coupon.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CouponVerifyRequestDto {
    @NotNull(message = "쿠폰 바코드는 필수입니다")
    private String barcodeNumber;
}
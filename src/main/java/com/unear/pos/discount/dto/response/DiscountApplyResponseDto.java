package com.unear.pos.discount.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DiscountApplyResponseDto {
    private String discountCode;
    private Long discountAmount;
    private Long finalAmount;
}

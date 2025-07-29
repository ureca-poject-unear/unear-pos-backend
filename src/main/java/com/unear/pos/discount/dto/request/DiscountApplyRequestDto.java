package com.unear.pos.discount.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountApplyRequestDto {
    @NotNull(message = "할인 정책 ID는 필수입니다")
    private Long discountPolicyId;

    @NotNull(message = "구매 금액은 필수입니다")
    @Min(value = 0, message = "구매 금액은 0 이상이어야 합니다")
    private Long purchaseAmount;
}
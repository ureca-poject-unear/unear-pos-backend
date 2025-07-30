package com.unear.pos.membership.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class MemberVerifyRequestDto {

    @NotBlank(message = "검증 타입은 필수입니다")
    @Pattern(regexp = "^(barcode|phone)$", message = "검증 타입은 barcode 또는 phone이어야 합니다")
    private String type;

    @NotBlank(message = "검증 값은 필수입니다")
    private String value;

    @NotNull(message = "구매 금액은 필수입니다")
    @Min(value = 1, message = "구매 금액은 1원 이상이어야 합니다")
    private Long purchaseAmount;
}


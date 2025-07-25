package com.unear.pos.membership.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberVerifyRequestDto {

    @NotBlank(message = "검증 타입은 필수입니다")
    @Pattern(regexp = "^(barcode|phone)$", message = "검증 타입은 barcode 또는 phone이어야 합니다")
    private String type;

    @NotBlank(message = "검증 값은 필수입니다")
    private String value;
}

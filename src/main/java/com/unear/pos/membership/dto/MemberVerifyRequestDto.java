package com.unear.pos.membership.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberVerifyRequestDto {
    private String type;
    private String value;
}

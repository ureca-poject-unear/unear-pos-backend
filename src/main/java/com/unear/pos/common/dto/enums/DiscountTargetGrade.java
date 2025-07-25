package com.unear.pos.common.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountTargetGrade {
    BASIC("BASIC", "우수"),
    VIP("VIP", "VIP"),
    VVIP("VVIP", "VVIP"),
    ALL("ALL", "모든등급");

    private final String code;
    private final String label;

    public static boolean isAll(String code) {
        return ALL.code.equalsIgnoreCase(code);
    }
}
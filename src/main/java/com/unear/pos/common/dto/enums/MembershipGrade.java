package com.unear.pos.common.dto.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MembershipGrade {
    BASIC("BASIC", "우수"),
    VIP("VIP", "VIP"),
    VVIP("VVIP", "VVIP"),
    ALL("ALL", "모든등급");

    private final String code;
    private final String label;

    public static MembershipGrade fromCode(String code) {
        return Arrays.stream(values())
                .filter(grade -> grade.code.equals(code))
                .findFirst()
                .orElse(BASIC);
    }

    public static boolean isAll(String code) {
        return ALL.code.equalsIgnoreCase(code);
    }
}

package com.unear.pos.common.dto.enums;

import com.unear.pos.common.exception.business.InvalidDataException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MembershipGrade {
    BASIC("BASIC", "우수"),
    VIP("VIP", "VIP"),
    VVIP("VVIP", "VVIP");

    private final String code;
    private final String label;

    public static MembershipGrade fromCode(String code) {
        if (code == null) {
            throw new InvalidDataException("유효하지 않은 멤버십 등급 코드입니다");
        }
        return Arrays.stream(values())
                .filter(grade -> grade.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new InvalidDataException("유효하지 않은 멤버십 등급 코드입니다: " + code));
    }
}

package com.unear.pos.common.dto.enums;

import com.unear.pos.common.exception.business.InvalidDataException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CouponStatus {
    UNUSED("UNUSED", "미사용"),
    EXPIRED("EXPIRED", "만료"),
    USED("USED", "사용됨");

    private final String code;
    private final String label;

    public static CouponStatus fromCode(String code) {
        return Arrays.stream(values())
                .filter(status -> status.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new InvalidDataException("유효하지 않은 쿠폰 상태 코드입니다: " + code));
    }

}

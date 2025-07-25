package com.unear.pos.common.dto.enums;

import com.unear.pos.common.exception.business.InvalidVerificationTypeException;

public enum VerificationType {
    BARCODE,
    PHONE;

    public static VerificationType fromString(String type) {
        try {
            return valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidVerificationTypeException("잘못된 타입입니다.");
        }
    }
}

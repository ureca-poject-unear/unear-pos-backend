package com.unear.pos.common.exception.business;

import com.unear.pos.common.exception.BusinessException;
import com.unear.pos.common.exception.ErrorCode;

public class InvalidVerificationTypeException extends BusinessException {
    public InvalidVerificationTypeException() {
        super(ErrorCode.INVALID_VERIFICATION_TYPE);
    }

    public InvalidVerificationTypeException(String message) {
        super(ErrorCode.INVALID_VERIFICATION_TYPE, message);
    }

    public InvalidVerificationTypeException(String message, Throwable cause) {
        super(ErrorCode.INVALID_VERIFICATION_TYPE, message, cause);
    }
}
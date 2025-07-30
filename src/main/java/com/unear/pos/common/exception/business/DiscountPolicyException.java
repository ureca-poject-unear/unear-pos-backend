package com.unear.pos.common.exception.business;

import com.unear.pos.common.exception.BusinessException;
import com.unear.pos.common.exception.ErrorCode;

public class DiscountPolicyException extends BusinessException {
    public DiscountPolicyException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DiscountPolicyException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public DiscountPolicyException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
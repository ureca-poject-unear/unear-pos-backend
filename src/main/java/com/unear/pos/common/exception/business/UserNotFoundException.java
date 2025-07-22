package com.unear.pos.common.exception.business;

import com.unear.pos.common.exception.BusinessException;
import com.unear.pos.common.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
    protected UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    protected UserNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    protected UserNotFoundException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}

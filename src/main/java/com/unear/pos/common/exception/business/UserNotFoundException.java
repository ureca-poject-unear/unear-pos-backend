package com.unear.pos.common.exception.business;

import com.unear.pos.common.exception.BusinessException;
import com.unear.pos.common.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public UserNotFoundException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}

package com.unear.pos.common.exception.business;

import com.unear.pos.common.exception.BusinessException;
import com.unear.pos.common.exception.ErrorCode;

public class InvalidDataException extends BusinessException {
    public InvalidDataException() {
        super(ErrorCode.INVALID_DATA);
    }

    public InvalidDataException(String message) {
        super(ErrorCode.INVALID_DATA, message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(ErrorCode.INVALID_DATA, message, cause);
    }
}




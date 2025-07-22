package com.unear.pos.common.exception;

public class BusinessException extends BaseException {
    protected BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }

    protected BusinessException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    protected BusinessException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}

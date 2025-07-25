package com.unear.pos.common.exception.business;

import com.unear.pos.common.exception.BusinessException;
import com.unear.pos.common.exception.ErrorCode;

public class OwnerNotFoundException extends BusinessException {
    public OwnerNotFoundException() {
        super(ErrorCode.OWNER_NOT_FOUND);
    }

    public OwnerNotFoundException(String message) {
        super(ErrorCode.OWNER_NOT_FOUND, message);
    }

    public OwnerNotFoundException(String message, Throwable cause) {
        super(ErrorCode.OWNER_NOT_FOUND, message, cause);
    }
}

package com.unear.pos.common.exception.business;

import com.unear.pos.common.exception.BusinessException;
import com.unear.pos.common.exception.ErrorCode;

public class MemberNotFoundException extends BusinessException {
    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }

    public MemberNotFoundException(String message) {
        super(ErrorCode.MEMBER_NOT_FOUND, message);
    }

    public MemberNotFoundException(String message, Throwable cause) {
        super(ErrorCode.MEMBER_NOT_FOUND, message, cause);
    }
}

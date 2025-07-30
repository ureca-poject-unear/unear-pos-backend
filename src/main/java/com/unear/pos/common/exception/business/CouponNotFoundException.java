package com.unear.pos.common.exception.business;

import com.unear.pos.common.exception.BusinessException;
import com.unear.pos.common.exception.ErrorCode;

public class CouponNotFoundException extends BusinessException {
    public CouponNotFoundException() {
        super(ErrorCode.COUPON_NOT_FOUND);
    }

    public CouponNotFoundException(String message) {
        super(ErrorCode.COUPON_NOT_FOUND, message);
    }

    public CouponNotFoundException(String message, Throwable cause) {
        super(ErrorCode.COUPON_NOT_FOUND, message, cause);
    }
}
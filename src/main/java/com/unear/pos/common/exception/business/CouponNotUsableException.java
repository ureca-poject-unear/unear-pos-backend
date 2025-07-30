package com.unear.pos.common.exception.business;

import com.unear.pos.common.exception.BusinessException;
import com.unear.pos.common.exception.ErrorCode;

public class CouponNotUsableException extends BusinessException {
    public CouponNotUsableException() {
        super(ErrorCode.COUPON_NOT_USABLE);
    }

    public CouponNotUsableException(String message) {
        super(ErrorCode.COUPON_NOT_USABLE, message);
    }

    public CouponNotUsableException(String message, Throwable cause) {
        super(ErrorCode.COUPON_NOT_USABLE, message, cause);
    }
}
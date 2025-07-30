package com.unear.pos.common.exception.business;

import com.unear.pos.common.exception.BusinessException;
import com.unear.pos.common.exception.ErrorCode;

public class CouponTemplateNotFoundException extends BusinessException {
    public CouponTemplateNotFoundException() {
        super(ErrorCode.COUPON_TEMPLATE_NOT_FOUND);
    }

    public CouponTemplateNotFoundException(String message) {
        super(ErrorCode.COUPON_TEMPLATE_NOT_FOUND, message);
    }

    public CouponTemplateNotFoundException(String message, Throwable cause) {
        super(ErrorCode.COUPON_TEMPLATE_NOT_FOUND, message, cause);
    }
}
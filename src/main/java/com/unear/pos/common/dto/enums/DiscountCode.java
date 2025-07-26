package com.unear.pos.common.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountCode {
    MEMBERSHIP_UNIT("MEMBERSHIP_UNIT"),
    MEMBERSHIP_FIXED("MEMBERSHIP_FIXED"),
    COUPON_PERCENT("COUPON_PERCENT"),
    COUPON_FIXED("COUPON_FIXED"),
    COUPON_FCFS("COUPON_FCFS");


    private final String code;

    public boolean isMembership() {
        return this == MEMBERSHIP_FIXED || this == MEMBERSHIP_UNIT;
    }

    public boolean isCoupon() {
        return this == COUPON_PERCENT || this == COUPON_FIXED;
    }

    public boolean isMembershipUnit() {
        return this == MEMBERSHIP_UNIT;
    }

    public boolean isMembershipFixed() {
        return this == MEMBERSHIP_FIXED;
    }

    public boolean isCouponPercent() {
        return this == COUPON_PERCENT;
    }

    public boolean isCouponFixed() {
        return this == COUPON_FIXED;
    }
}

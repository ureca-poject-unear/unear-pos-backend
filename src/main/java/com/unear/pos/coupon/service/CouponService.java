package com.unear.pos.coupon.service;

import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.coupon.dto.request.CouponVerifyRequestDto;
import com.unear.pos.coupon.dto.response.CouponVerifyResponseDto;
import com.unear.pos.coupon.entity.CouponTemplate;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import jakarta.servlet.http.HttpSession;

public interface CouponService {

    CouponVerifyResponseDto verifyCoupon(CouponVerifyRequestDto request, PosSessionInfo posInfo, HttpSession session);

    DiscountPolicyInfo validateCouponAndGetPolicy(CouponTemplate template, PosSessionInfo posInfo,
                                                  MembershipGrade memberGrade);
}

package com.unear.pos.discount.service;

import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.coupon.dto.request.CouponApplyRequestDto;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import com.unear.pos.discount.dto.request.DiscountApplyRequestDto;
import com.unear.pos.discount.dto.response.DiscountApplyResponseDto;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public interface DiscountService {
    List<DiscountPolicyInfo> getDiscountPolicies(MembershipGrade memberGrade, PosSessionInfo posInfo);

    DiscountApplyResponseDto applyMembershipDiscount(DiscountApplyRequestDto request, PosSessionInfo posInfo,
                                                     HttpSession session);

    DiscountApplyResponseDto applyCouponDiscount(CouponApplyRequestDto request, PosSessionInfo posInfo,
                                                 HttpSession session);
}

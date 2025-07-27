package com.unear.pos.coupon.service.impl;

import com.unear.pos.common.dto.MemberSession;
import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.common.dto.enums.PlaceType;
import com.unear.pos.coupon.dto.request.CouponVerifyRequestDto;
import com.unear.pos.coupon.dto.response.CouponVerifyResponseDto;
import com.unear.pos.coupon.entity.CouponTemplate;
import com.unear.pos.coupon.entity.UserCoupon;
import com.unear.pos.coupon.repository.CouponTemplateRepository;
import com.unear.pos.coupon.repository.UserCouponRepository;
import com.unear.pos.coupon.service.CouponService;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import com.unear.pos.discount.entity.FranchiseDiscountPolicy;
import com.unear.pos.discount.entity.GeneralDiscountPolicy;
import com.unear.pos.discount.repository.FranchiseDiscountPolicyRepository;
import com.unear.pos.discount.repository.GeneralDiscountPolicyRepository;
import com.unear.pos.discount.service.DiscountCalculationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final UserCouponRepository userCouponRepository;
    private final CouponTemplateRepository couponTemplateRepository;
    private final GeneralDiscountPolicyRepository generalDiscountPolicyRepository;
    private final FranchiseDiscountPolicyRepository franchiseDiscountPolicyRepository;
    private final DiscountCalculationService discountCalculationService;


    @Override
    public CouponVerifyResponseDto verifyCoupon(CouponVerifyRequestDto request, PosSessionInfo posInfo,
                                                HttpSession session) {

        MemberSession memberSession = (MemberSession) session.getAttribute("memberSession");
        if (memberSession == null) {
            throw new IllegalStateException("회원 인증이 필요합니다");
        }

        UserCoupon userCoupon = userCouponRepository
                .findByBarcodeNumber(request.getBarcodeNumber())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쿠폰입니다"));

        CouponTemplate template = couponTemplateRepository.findById(userCoupon.getCouponTemplateId())
                .orElseThrow(() -> new IllegalArgumentException("쿠폰 정보를 찾을 수 없습니다"));

        DiscountPolicyInfo policy = validateCouponAndGetPolicy(template, posInfo, memberSession.getMemberGrade());

        return CouponVerifyResponseDto.builder()
                .userCouponId(userCoupon.getUserCouponId())
                .discountCode(policy.getDiscountCode())
                .unitBaseAmount(policy.getUnitBaseAmount())
                .fixedDiscount(policy.getFixedDiscount())
                .discountPercent(policy.getDiscountPercent())
                .minPurchaseAmount(policy.getMinPurchaseAmount())
                .maxDiscountAmount(policy.getMaxDiscountAmount())
                .build();
    }


    public DiscountPolicyInfo validateCouponAndGetPolicy(CouponTemplate template, PosSessionInfo posInfo,
                                                         MembershipGrade memberGrade) {

        if (template.isExpired()) {
            throw new IllegalArgumentException("유효기간이 만료된 쿠폰입니다");
        }

        if (!template.isAvailable()) {
            throw new IllegalArgumentException("사용할 수 없는 쿠폰입니다");
        }

        if (!isValidMembershipGrade(template.getMembershipCode(), memberGrade)) {
            throw new IllegalArgumentException("회원 등급이 맞지 않는 쿠폰입니다");
        }

        PlaceType templatePlaceType = PlaceType.valueOf(template.getMarkerCode());
        return validateStoreMatchAndGetPolicy(template, posInfo, templatePlaceType);
    }

    private DiscountPolicyInfo validateStoreMatchAndGetPolicy(CouponTemplate template, PosSessionInfo posInfo,
                                                              PlaceType templatePlaceType) {
        if (templatePlaceType.isGeneralPolicy()) {
            GeneralDiscountPolicy generalPolicy = generalDiscountPolicyRepository.findById(
                            template.getDiscountPolicyDetailId())
                    .orElseThrow(() -> new IllegalArgumentException("할인 정책을 찾을 수 없습니다"));

            if (!generalPolicy.getPlaceId().equals(posInfo.getPlaceId())) {
                throw new IllegalArgumentException("이 가게에서 사용할 수 없는 쿠폰입니다");
            }

            return DiscountPolicyInfo.from(generalPolicy);

        } else if (templatePlaceType.isFranchisePolicy()) {
            FranchiseDiscountPolicy franchisePolicy = franchiseDiscountPolicyRepository.findById(
                            template.getDiscountPolicyDetailId())
                    .orElseThrow(() -> new IllegalArgumentException("할인 정책을 찾을 수 없습니다"));

            if (!franchisePolicy.getFranchiseId().equals(posInfo.getFranchiseId())) {
                throw new IllegalArgumentException("이 프랜차이즈에서 사용할 수 없는 쿠폰입니다");
            }

            return DiscountPolicyInfo.from(franchisePolicy);
        }

        throw new IllegalArgumentException("적용할 수 없는 할인 정책입니다");
    }


    private boolean isValidMembershipGrade(String templateMembershipCode, MembershipGrade memberGrade) {

        if ("ALL".equals(templateMembershipCode)) {
            return true;
        }
        return templateMembershipCode.equals(memberGrade.getCode());
    }
}

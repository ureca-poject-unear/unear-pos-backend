package com.unear.pos.discount.service.impl;

import com.unear.pos.common.dto.MemberSession;
import com.unear.pos.common.dto.Money;
import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.dto.enums.CouponStatus;
import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.common.dto.enums.PlaceType;
import com.unear.pos.common.util.MemberSessionUtil;
import com.unear.pos.coupon.dto.request.CouponApplyRequestDto;
import com.unear.pos.coupon.entity.CouponTemplate;
import com.unear.pos.coupon.entity.UserCoupon;
import com.unear.pos.coupon.repository.CouponTemplateRepository;
import com.unear.pos.coupon.repository.UserCouponRepository;
import com.unear.pos.coupon.service.CouponService;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import com.unear.pos.discount.dto.request.DiscountApplyRequestDto;
import com.unear.pos.discount.dto.response.DiscountApplyResponseDto;
import com.unear.pos.discount.service.DiscountCalculationService;
import com.unear.pos.discount.service.DiscountService;
import com.unear.pos.discount.strategy.provider.DiscountPolicyStrategy;
import com.unear.pos.discount.strategy.provider.FranchiseDiscountPolicyStrategy;
import com.unear.pos.discount.strategy.provider.GeneralDiscountPolicyStrategy;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final GeneralDiscountPolicyStrategy generalStrategy;
    private final FranchiseDiscountPolicyStrategy franchiseStrategy;
    private final DiscountCalculationService discountCalculationService;
    private final CouponService couponService;
    private final UserCouponRepository userCouponRepository;
    private final CouponTemplateRepository couponTemplateRepository;
    private final MemberSessionUtil memberSessionUtil;

    @Override
    public List<DiscountPolicyInfo> getDiscountPolicies(MembershipGrade memberGrade, PosSessionInfo posInfo) {
        DiscountPolicyStrategy strategy = selectStrategy(posInfo.getPlaceType());
        Long targetId = getTargetId(posInfo, posInfo.getPlaceType());

        return strategy.getDiscountPolicies(memberGrade, targetId, posInfo.getEventStatus());
    }

    @Override
    public DiscountApplyResponseDto applyMembershipDiscount(DiscountApplyRequestDto request, PosSessionInfo posInfo,
                                                            HttpSession session) {

        MemberSession memberSession = memberSessionUtil.validateAndGetMemberSession(session);

        if (memberSession.getMembershipDiscount() != null) {
            throw new IllegalStateException("이미 멤버십 할인이 적용되었습니다");
        }
        if (memberSession.getCouponDiscount() != null) {
            throw new IllegalStateException("이미 쿠폰 할인이 적용되었습니다");
        }

        MembershipGrade memberGrade = memberSession.getMemberGrade();

        List<DiscountPolicyInfo> availablePolicies = getDiscountPolicies(memberGrade, posInfo);

        DiscountPolicyInfo selectedPolicy = availablePolicies.stream()
                .filter(policy -> policy.getId().equals(request.getDiscountPolicyId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("적용할 수 없는 할인 정책입니다"));

        Money purchaseAmount = Money.of(memberSession.getPurchaseAmount());
        Money discountAmount = discountCalculationService.calculateDiscount(purchaseAmount, selectedPolicy);
        Money finalAmount = purchaseAmount.subtract(discountAmount);

        DiscountApplyResponseDto responseDto = DiscountApplyResponseDto.builder()
                .discountCode(selectedPolicy.getDiscountCode())
                .discountAmount(discountAmount.getAmount().longValue())
                .finalAmount(finalAmount.getAmount().longValue())
                .build();

        MemberSession updated = memberSession.withMembershipDiscount(responseDto);
        memberSessionUtil.updateMemberSession(session, updated);
        return responseDto;
    }

    public DiscountApplyResponseDto applyCouponDiscount(CouponApplyRequestDto request, PosSessionInfo posInfo,
                                                        HttpSession session) {

        MemberSession memberSession = memberSessionUtil.validateAndGetMemberSession(session);

        if (memberSession.getCouponDiscount() != null) {
            throw new IllegalStateException("이미 쿠폰 할인이 적용되었습니다");
        }
        if (memberSession.getMembershipDiscount() != null) {
            throw new IllegalStateException("이미 멤버십 할인이 적용되었습니다");
        }

        UserCoupon userCoupon = userCouponRepository.findById(request.getUserCouponId())
                .orElseThrow(() -> new IllegalArgumentException("쿠폰을 찾을 수 없습니다"));

        if (!userCoupon.getUserId().equals(memberSession.getMemberId())
                || !CouponStatus.UNUSED.name().equals(userCoupon.getCouponStatusCode())) {
            throw new IllegalArgumentException("사용할 수 없는 쿠폰입니다");
        }

        CouponTemplate template = couponTemplateRepository.findById(userCoupon.getCouponTemplateId())
                .orElseThrow(() -> new IllegalArgumentException("쿠폰 정보를 찾을 수 없습니다"));

        DiscountPolicyInfo policy = couponService.validateCouponAndGetPolicy(template, posInfo,
                memberSession.getMemberGrade());

        Money purchaseAmount = Money.of(memberSession.getPurchaseAmount());
        Money discountAmount = discountCalculationService.calculateDiscount(purchaseAmount, policy);
        Money finalAmount = purchaseAmount.subtract(discountAmount);

        DiscountApplyResponseDto responseDto = DiscountApplyResponseDto.builder()
                .discountCode(policy.getDiscountCode())
                .discountAmount(discountAmount.getAmount().longValue())
                .finalAmount(finalAmount.getAmount().longValue())
                .build();

        MemberSession updated = memberSession.withCouponDiscount(responseDto, request.getUserCouponId());
        memberSessionUtil.updateMemberSession(session, updated);
        return responseDto;
    }


    private DiscountPolicyStrategy selectStrategy(PlaceType placeType) {
        if (placeType.isGeneralPolicy()) {
            return generalStrategy;
        } else if (placeType.isFranchisePolicy()) {
            return franchiseStrategy;
        }
        throw new IllegalArgumentException("지원하지 않는 장소 타입: " + placeType);

    }

    private Long getTargetId(PosSessionInfo posInfo, PlaceType placeType) {
        if (placeType.isGeneralPolicy()) {
            return posInfo.getPlaceId();
        } else if (placeType.isFranchisePolicy()) {
            return posInfo.getFranchiseId();
        }
        throw new IllegalArgumentException("지원하지 않는 장소 타입: " + placeType);
    }
}

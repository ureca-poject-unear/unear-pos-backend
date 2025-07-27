package com.unear.pos.discount.service.impl;

import com.unear.pos.common.dto.MemberSession;
import com.unear.pos.common.dto.Money;
import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.common.dto.enums.PlaceType;
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

    @Override
    public List<DiscountPolicyInfo> getDiscountPolicies(MembershipGrade memberGrade, PosSessionInfo posInfo) {
        DiscountPolicyStrategy strategy = selectStrategy(posInfo.getPlaceType());
        Long targetId = getTargetId(posInfo, posInfo.getPlaceType());

        return strategy.getDiscountPolicies(memberGrade, targetId, posInfo.getEventStatus());
    }

    @Override
    public DiscountApplyResponseDto applyDiscount(DiscountApplyRequestDto request, PosSessionInfo posInfo,
                                                  HttpSession session) {

        MemberSession memberSession = (MemberSession) session.getAttribute("memberSession");
        if (memberSession == null) {
            throw new IllegalStateException("회원 인증이 필요합니다");
        }

        MembershipGrade memberGrade = memberSession.getMemberGrade();

        List<DiscountPolicyInfo> availablePolicies = getDiscountPolicies(memberGrade, posInfo);

        DiscountPolicyInfo selectedPolicy = availablePolicies.stream()
                .filter(policy -> policy.getId().equals(request.getDiscountPolicyId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("적용할 수 없는 할인 정책입니다"));

        Money purchaseAmount = Money.of(request.getPurchaseAmount());
        if (purchaseAmount.isLessThan(Money.zero()) || purchaseAmount.equals(Money.zero())) {
            throw new IllegalArgumentException("구매 금액은 0보다 커야합니다.");
        }

        Money discountAmount = discountCalculationService.calculateDiscount(purchaseAmount, selectedPolicy);
        Money finalAmount = purchaseAmount.subtract(discountAmount);

        return DiscountApplyResponseDto.builder()
                .discountCode(selectedPolicy.getDiscountCode())
                .discountAmount(discountAmount.getAmount().longValue())
                .finalAmount(finalAmount.getAmount().longValue())
                .build();

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

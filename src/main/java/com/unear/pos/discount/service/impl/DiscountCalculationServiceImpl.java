package com.unear.pos.discount.service.impl;

import com.unear.pos.common.dto.Money;
import com.unear.pos.common.dto.enums.DiscountCode;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import com.unear.pos.discount.service.DiscountCalculationService;
import com.unear.pos.discount.strategy.calculator.DiscountCalculationStrategy;
import com.unear.pos.discount.strategy.calculator.MembershipFixedDiscountStrategy;
import com.unear.pos.discount.strategy.calculator.MembershipUnitDiscountStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiscountCalculationServiceImpl implements DiscountCalculationService {

    private final MembershipUnitDiscountStrategy membershipUnitStrategy;
    private final MembershipFixedDiscountStrategy membershipFixedStrategy;

    @Override
    public Money calculateDiscount(Money purchaseAmount, DiscountPolicyInfo policy) {
        DiscountCode discountCode = DiscountCode.valueOf(policy.getDiscountCode());

        DiscountCalculationStrategy strategy = selectStrategy(discountCode);
        return strategy.calculateDiscount(purchaseAmount, policy);
    }

    private DiscountCalculationStrategy selectStrategy(DiscountCode discountCode) {
        return switch (discountCode) {
            case MEMBERSHIP_UNIT -> membershipUnitStrategy;
            case MEMBERSHIP_FIXED -> membershipFixedStrategy;
            default -> throw new IllegalArgumentException("지원하지 않는 할인 코드: " + discountCode);
        };
    }
}

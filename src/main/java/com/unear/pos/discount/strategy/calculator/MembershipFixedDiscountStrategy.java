package com.unear.pos.discount.strategy.calculator;

import com.unear.pos.common.dto.Money;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import org.springframework.stereotype.Component;

@Component
public class MembershipFixedDiscountStrategy implements DiscountCalculationStrategy {
    @Override
    public Money calculateDiscount(Money purchaseAmount, DiscountPolicyInfo policy) {
        if (policy.getFixedDiscount() == null) {
            return Money.zero();
        }

        if (policy.getMinPurchaseAmount() != null) {
            Money minAmount = Money.of(policy.getMinPurchaseAmount());
            if (purchaseAmount.isLessThan(minAmount)) {
                return Money.zero();
            }
        }

        Money discount = Money.of(policy.getFixedDiscount());

        if (discount.isGreaterThan(purchaseAmount)) {
            return purchaseAmount;
        }

        return discount;
    }
}

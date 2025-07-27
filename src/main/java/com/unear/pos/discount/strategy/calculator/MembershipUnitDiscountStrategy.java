package com.unear.pos.discount.strategy.calculator;

import com.unear.pos.common.dto.Money;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class MembershipUnitDiscountStrategy implements DiscountCalculationStrategy {

    private static final BigDecimal DISCOUNT_BASE_UNIT = BigDecimal.valueOf(1000); // 1000원 단위

    @Override
    public Money calculateDiscount(Money purchaseAmount, DiscountPolicyInfo policy) {
        if (policy.getUnitBaseAmount() == null) {
            return Money.zero();
        }

        if (policy.getMinPurchaseAmount() != null) {
            Money minAmount = Money.of(policy.getMinPurchaseAmount());
            if (purchaseAmount.isLessThan(minAmount)) {
                return Money.zero();
            }
        }

        BigDecimal discountUnits = purchaseAmount.getAmount().divide(DISCOUNT_BASE_UNIT, 0, RoundingMode.DOWN);
        Money discount = Money.of(discountUnits.multiply(BigDecimal.valueOf(policy.getUnitBaseAmount())));

        if (policy.getMaxDiscountAmount() != null) {
            Money maxDiscount = Money.of(policy.getMaxDiscountAmount());

            if (discount.isGreaterThan(maxDiscount)) {
                return maxDiscount;
            }
        }

        return discount;
    }
}

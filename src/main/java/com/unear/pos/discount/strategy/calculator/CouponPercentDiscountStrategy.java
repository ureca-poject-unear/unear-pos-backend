package com.unear.pos.discount.strategy.calculator;

import com.unear.pos.common.dto.Money;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;


@Component
public class CouponPercentDiscountStrategy implements DiscountCalculationStrategy {
    @Override
    public Money calculateDiscount(Money purchaseAmount, DiscountPolicyInfo policy) {
        if (policy.getDiscountPercent() == null) {
            return Money.zero();
        }

        if (policy.getMinPurchaseAmount() != null) {
            Money minAmount = Money.of(policy.getMinPurchaseAmount());
            if (purchaseAmount.isLessThan(minAmount)) {
                return Money.zero();
            }
        }

        BigDecimal discountRate = BigDecimal.valueOf(policy.getDiscountPercent())
                .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        Money discount = purchaseAmount.multiply(discountRate);

        if (policy.getMaxDiscountAmount() != null) {
            Money maxDiscount = Money.of(policy.getMaxDiscountAmount());

            if (discount.isGreaterThan(maxDiscount)) {
                return maxDiscount;
            }
        }

        return discount;

    }
}

package com.unear.pos.discount.strategy.calculator;

import com.unear.pos.common.dto.Money;
import com.unear.pos.discount.dto.DiscountPolicyInfo;

public interface DiscountCalculationStrategy {
    Money calculateDiscount(Money purchaseAmount, DiscountPolicyInfo policy);
}

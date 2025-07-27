package com.unear.pos.discount.service;

import com.unear.pos.common.dto.Money;
import com.unear.pos.discount.dto.DiscountPolicyInfo;

public interface DiscountCalculationService {
    Money calculateDiscount(Money purchaseAmount, DiscountPolicyInfo policy);
}

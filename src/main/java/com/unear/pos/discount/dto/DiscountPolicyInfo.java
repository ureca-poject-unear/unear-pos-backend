package com.unear.pos.discount.dto;

import com.unear.pos.discount.entity.FranchiseDiscountPolicy;
import com.unear.pos.discount.entity.GeneralDiscountPolicy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DiscountPolicyInfo {
    private String discountCode;
    private Integer unitBaseAmount;
    private Integer fixedDiscount;
    private Integer discountPercent;
    private Integer minPurchaseAmount;
    private Integer maxDiscountAmount;
    private String membershipCode;

    public static DiscountPolicyInfo from(GeneralDiscountPolicy policy) {
        return DiscountPolicyInfo.builder()
                .discountCode(policy.getDiscountCode())
                .unitBaseAmount(policy.getUnitBaseAmount())
                .fixedDiscount(policy.getFixedDiscount())
                .discountPercent(policy.getDiscountPercent())
                .minPurchaseAmount(policy.getMinPurchaseAmount())
                .maxDiscountAmount(policy.getMaxDiscountAmount())
                .membershipCode(policy.getMembershipCode())
                .build();
    }

    public static DiscountPolicyInfo from(FranchiseDiscountPolicy policy) {
        return DiscountPolicyInfo.builder()
                .discountCode(policy.getDiscountCode())
                .unitBaseAmount(policy.getUnitBaseAmount())
                .fixedDiscount(policy.getFixedDiscount())
                .discountPercent(policy.getDiscountPercent())
                .minPurchaseAmount(policy.getMinPurchaseAmount())
                .maxDiscountAmount(policy.getMaxDiscountAmount())
                .membershipCode(policy.getMembershipCode())
                .build();
    }
}
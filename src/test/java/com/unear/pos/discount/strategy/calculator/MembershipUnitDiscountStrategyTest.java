package com.unear.pos.discount.strategy.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import com.unear.pos.common.dto.Money;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MembershipUnitDiscountStrategyTest {

    private MembershipUnitDiscountStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new MembershipUnitDiscountStrategy();
    }

    private DiscountPolicyInfo buildPolicy(int unitBaseAmount, Integer maxDiscountAmount) {
        return DiscountPolicyInfo.builder()
                .unitBaseAmount(unitBaseAmount)
                .maxDiscountAmount(maxDiscountAmount)
                .build();
    }

    @Test
    void 구매금액_5500원_단위당_100원_예상_500원() {
        Money result = strategy.calculateDiscount(
                Money.of(5500L),
                buildPolicy(100, null)
        );
        assertThat(result).isEqualTo(Money.of(500));
    }

    @Test
    void 구매금액_1000원_단위당_100원_예상_100원() {
        Money result = strategy.calculateDiscount(
                Money.of(1000L),
                buildPolicy(100, null)
        );
        assertThat(result).isEqualTo(Money.of(100));
    }

    @Test
    void 구매금액_999원_단위당_100원_예상_0원() {
        Money result = strategy.calculateDiscount(
                Money.of(999L),
                buildPolicy(100, null)
        );
        assertThat(result).isEqualTo(Money.zero());
    }

    @Test
    void 구매금액_2001원_단위당_100원_예상_200원() {
        Money result = strategy.calculateDiscount(
                Money.of(2001L),
                buildPolicy(100, null)
        );
        assertThat(result).isEqualTo(Money.of(200));
    }
}
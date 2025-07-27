package com.unear.pos.discount.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "general_discount_policy")
@Getter
@NoArgsConstructor
public class GeneralDiscountPolicy {

    @Id
    @Column(name = "general_discount_policy_id")
    private Long id;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "membership_code")
    private String membershipCode;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "unit_base_amount")
    private Integer unitBaseAmount;

    @Column(name = "fixed_discount")
    private Integer fixedDiscount;

    @Column(name = "discount_percent")
    private Integer discountPercent;

    @Column(name = "min_purchase_amount")
    private Integer minPurchaseAmount;

    @Column(name = "max_discount_amount")
    private Integer maxDiscountAmount;

    @Column(name = "marker_code")
    private String markerCode;
}
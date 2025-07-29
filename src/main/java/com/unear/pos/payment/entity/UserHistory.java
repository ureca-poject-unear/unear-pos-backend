package com.unear.pos.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_histories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_history_id")
    private Long userHistoryId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_coupon_id")
    private Long userCouponId;

    @Column(name = "place_id", nullable = false)
    private Long placeId;

    @Column(name = "used_at")
    private LocalDate usedAt;

    @Column(name = "original_amount")
    private Integer originalAmount;

    @Column(name = "membership_discount_amount")
    private Integer membershipDiscountAmount;

    @Column(name = "coupon_discount_amount")
    private Integer couponDiscountAmount;

    @Column(name = "total_discount_amount")
    private Integer totalDiscountAmount;

    @Column(name = "total_payment_amount")
    private Integer totalPaymentAmount;

    @Column(name = "is_coupon_used")
    private Boolean isCouponUsed;

    @Column(name = "is_membership_used")
    private Boolean isMembershipUsed;

    @Column(name = "paid_at")
    private LocalDate paidAt;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "membership_code")
    private String membershipCode;

    @Column(name = "place_category") // 또는 category_code
    private String placeCategory;
}
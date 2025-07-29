package com.unear.pos.coupon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupon_templates")
@Getter
@NoArgsConstructor
public class CouponTemplate {
    @Id
    @Column(name = "coupon_template_id")
    private Long couponTemplateId;

    @Column(name = "discount_policy_detail_id")
    private Long discountPolicyDetailId;

    @Column(name = "coupon_name")
    private String couponName;

    @Column(name = "remaining_quantity")
    private Integer remainingQuantity;

    @Column(name = "coupon_start")
    private LocalDateTime couponStart;

    @Column(name = "coupon_end")
    private LocalDateTime couponEnd;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "membership_code")
    private String membershipCode;

    @Column(name = "marker_code")
    private String markerCode; // BASIC, LOCAL, FRANCHISE

    @Column(name = "unear_event_id")
    private Long unearEventId;

    public boolean isExpired() {
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(couponStart) || now.isAfter(couponEnd);
    }

    public boolean isAvailable() {
        return remainingQuantity == null || remainingQuantity == -1 || remainingQuantity > 0;
    }
}
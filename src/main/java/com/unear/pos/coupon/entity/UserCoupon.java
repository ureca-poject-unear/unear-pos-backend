package com.unear.pos.coupon.entity;

import com.unear.pos.common.dto.enums.CouponStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_coupons")
@Getter
@NoArgsConstructor
public class UserCoupon {

    @Id
    @Column(name = "user_coupon_id")
    private Long userCouponId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "coupon_template_id")
    private Long couponTemplateId;

    @Column(name = "coupon_status_code")
    private String couponStatusCode;

    @Column(name = "barcode_number")
    private String barcodeNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "used_at")
    private LocalDateTime usedAt;


    public boolean isUsable() {
        return CouponStatus.UNUSED.getCode().equals(this.couponStatusCode);
    }

    public void markAsUsed() {
        this.couponStatusCode = CouponStatus.USED.getCode();
        this.usedAt = LocalDateTime.now();
    }

    public boolean isOwnedBy(Long memberId) {
        return this.userId != null && this.userId.equals(memberId);
    }
}

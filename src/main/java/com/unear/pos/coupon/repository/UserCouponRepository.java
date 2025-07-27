package com.unear.pos.coupon.repository;

import com.unear.pos.coupon.entity.UserCoupon;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
    Optional<UserCoupon> findByBarcodeNumber(String barcodeNumber);
}

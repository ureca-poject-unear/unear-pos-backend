package com.unear.pos.discount.repository;

import com.unear.pos.discount.entity.GeneralDiscountPolicy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralDiscountPolicyRepository extends JpaRepository<GeneralDiscountPolicy, Long> {
    List<GeneralDiscountPolicy> findByPlaceIdAndMembershipCode(Long placeId, String code);
}

package com.unear.pos.discount.repository;

import com.unear.pos.discount.entity.FranchiseDiscountPolicy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseDiscountPolicyRepository extends JpaRepository<FranchiseDiscountPolicy, Long> {
    List<FranchiseDiscountPolicy> findByFranchiseIdAndMembershipCode(Long franchiseId, String code);
}

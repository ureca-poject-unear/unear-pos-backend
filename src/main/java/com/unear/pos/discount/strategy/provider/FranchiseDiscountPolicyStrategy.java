package com.unear.pos.discount.strategy.provider;

import com.unear.pos.common.dto.enums.DiscountCode;
import com.unear.pos.common.dto.enums.DiscountTargetGrade;
import com.unear.pos.common.dto.enums.EventParticipationStatus;
import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import com.unear.pos.discount.entity.FranchiseDiscountPolicy;
import com.unear.pos.discount.repository.FranchiseDiscountPolicyRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FranchiseDiscountPolicyStrategy implements DiscountPolicyStrategy {

    private final FranchiseDiscountPolicyRepository repository;

    @Override
    public List<DiscountPolicyInfo> getDiscountPolicies(MembershipGrade memberGrade, Long franchiseId,
                                                        EventParticipationStatus eventStatus) {

        List<FranchiseDiscountPolicy> allGradePolicies = repository.findByFranchiseIdAndMembershipCode(franchiseId,
                DiscountTargetGrade.ALL.getCode());

        DiscountTargetGrade targetGrade = DiscountTargetGrade.fromMembershipGrade(memberGrade);
        List<FranchiseDiscountPolicy> specificGradePolicies = repository.findByFranchiseIdAndMembershipCode(franchiseId,
                targetGrade.getCode());

        return Stream.concat(allGradePolicies.stream(), specificGradePolicies.stream())
                .filter(policy -> DiscountCode.valueOf(policy.getDiscountCode()).isMembership())
                .map(DiscountPolicyInfo::from)
                .collect(Collectors.toList());
    }
}

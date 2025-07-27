package com.unear.pos.discount.strategy;

import com.unear.pos.common.dto.enums.DiscountCode;
import com.unear.pos.common.dto.enums.DiscountTargetGrade;
import com.unear.pos.common.dto.enums.EventParticipationStatus;
import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import com.unear.pos.discount.entity.GeneralDiscountPolicy;
import com.unear.pos.discount.repository.GeneralDiscountPolicyRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeneralDiscountPolicyStrategy implements DiscountPolicyStrategy {

    private final GeneralDiscountPolicyRepository repository;

    @Override
    public List<DiscountPolicyInfo> getDiscountPolicies(MembershipGrade memberGrade, Long placeId,
                                                        EventParticipationStatus eventStatus) {
        /**
         * 1. 전체 등급이 적용 가능한 쿠폰이 존재하는지 조회
         */
        List<GeneralDiscountPolicy> allGradePolicies = repository.findByPlaceIdAndMembershipCode(placeId,
                DiscountTargetGrade.ALL.getCode());

        /**
         * 2. 특정 등급 정책 조회
         */
        DiscountTargetGrade targetGrade = DiscountTargetGrade.fromMembershipGrade(memberGrade);
        List<GeneralDiscountPolicy> specificGradePolicies = repository.findByPlaceIdAndMembershipCode(placeId,
                targetGrade.getCode());

        return Stream.concat(allGradePolicies.stream(), specificGradePolicies.stream())
                .filter(policy -> DiscountCode.valueOf(policy.getDiscountCode()).isMembership())
                .map(DiscountPolicyInfo::from)
                .collect(Collectors.toList());
    }

}

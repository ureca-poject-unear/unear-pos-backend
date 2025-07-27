package com.unear.pos.discount.strategy.provider;

import com.unear.pos.common.dto.enums.EventParticipationStatus;
import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import java.util.List;

public interface DiscountPolicyStrategy {
    List<DiscountPolicyInfo> getDiscountPolicies(MembershipGrade memberGrade, Long targetId,
                                                 EventParticipationStatus eventStatus);
}

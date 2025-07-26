package com.unear.pos.discount.service.impl;

import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.common.dto.enums.PlaceType;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import com.unear.pos.discount.service.DiscountService;
import com.unear.pos.discount.strategy.DiscountPolicyStrategy;
import com.unear.pos.discount.strategy.FranchiseDiscountPolicyStrategy;
import com.unear.pos.discount.strategy.GeneralDiscountPolicyStrategy;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final GeneralDiscountPolicyStrategy generalStrategy;
    private final FranchiseDiscountPolicyStrategy franchiseStrategy;

    @Override
    public List<DiscountPolicyInfo> getDiscountPolicies(MembershipGrade memberGrade, PosSessionInfo posInfo) {
        DiscountPolicyStrategy strategy = selectStrategy(posInfo.getPlaceType());
        Long targetId = getTargetId(posInfo, posInfo.getPlaceType());

        return strategy.getDiscountPolicies(memberGrade, targetId, posInfo.getEventStatus());
    }

    private DiscountPolicyStrategy selectStrategy(PlaceType placeType) {
        if (placeType.isGeneralPolicy()) {
            return generalStrategy;
        } else if (placeType.isFranchisePolicy()) {
            return franchiseStrategy;
        }
        throw new IllegalArgumentException("지원하지 않는 장소 타입: " + placeType);

    }

    private Long getTargetId(PosSessionInfo posInfo, PlaceType placeType) {
        if (placeType.isGeneralPolicy()) {
            return posInfo.getPlaceId();
        } else if (placeType.isFranchisePolicy()) {
            return posInfo.getFranchiseId();
        }
        throw new IllegalArgumentException("지원하지 않는 장소 타입: " + placeType);
    }
}

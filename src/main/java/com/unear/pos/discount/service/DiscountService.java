package com.unear.pos.discount.service;

import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import java.util.List;

public interface DiscountService {
    List<DiscountPolicyInfo> getDiscountPolicies(MembershipGrade memberGrade, PosSessionInfo posInfo);

}

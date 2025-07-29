package com.unear.pos.membership.service;

import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.member.dto.MemberInfo;
import com.unear.pos.membership.dto.MemberVerifyRequestDto;
import jakarta.servlet.http.HttpSession;

public interface MembershipService {
    MemberInfo verifyMember(MemberVerifyRequestDto request, PosSessionInfo posInfo, HttpSession session);

    void cancelMembershipDiscount(HttpSession session);
}

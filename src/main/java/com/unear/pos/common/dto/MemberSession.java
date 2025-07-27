package com.unear.pos.common.dto;

import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.member.dto.MemberInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSession {
    private Long memberId;
    private MembershipGrade memberGrade;
    private Long placeId;
    private String memberName;

    public static MemberSession from(MemberInfo memberInfo, PosSessionInfo posInfo) {
        return new MemberSession(
                memberInfo.getMemberId(),
                memberInfo.getMemberGrade(),
                posInfo.getPlaceId(),
                memberInfo.getMemberName()
        );
    }
}
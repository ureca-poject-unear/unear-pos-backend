package com.unear.pos.member.dto;

import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberInfo {
    private Long memberId;
    private String memberName;
    private MembershipGrade memberGrade;

    public static MemberInfo from(Member member) {
        return MemberInfo.builder()
                .memberId(member.getUserId())
                .memberName(maskName(member.getName()))
                .memberGrade(MembershipGrade.fromCode(member.getMembershipCode()))
                .build();
    }

    private static String maskName(String name) {
        if (name.length() <= 1) {
            return name;
        }
        if (name.length() == 2) {
            return name.charAt(0) + "*";
        }
        return name.charAt(0) + "*".repeat(name.length() - 2) + name.charAt(name.length() - 1);
    }
}

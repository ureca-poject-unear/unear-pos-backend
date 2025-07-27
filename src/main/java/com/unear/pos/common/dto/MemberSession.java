package com.unear.pos.common.dto;

import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.discount.dto.response.DiscountApplyResponseDto;
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

    private DiscountApplyResponseDto membershipDiscount;
    private DiscountApplyResponseDto couponDiscount;
    private Long totalDiscountAmount;

    public static MemberSession from(MemberInfo memberInfo, PosSessionInfo posInfo) {
        return new MemberSession(
                memberInfo.getMemberId(),
                memberInfo.getMemberGrade(),
                posInfo.getPlaceId(),
                memberInfo.getMemberName(),
                null,
                null,
                0L
        );
    }


    public MemberSession withMembershipDiscount(DiscountApplyResponseDto discount) {
        return new MemberSession(memberId, memberGrade, placeId, memberName,
                discount, this.couponDiscount, calculateTotal(discount, this.couponDiscount));
    }

    public MemberSession withCouponDiscount(DiscountApplyResponseDto discount) {
        return new MemberSession(memberId, memberGrade, placeId, memberName,
                this.membershipDiscount, discount, calculateTotal(this.membershipDiscount, discount));
    }

    private Long calculateTotal(DiscountApplyResponseDto membership, DiscountApplyResponseDto coupon) {
        long total = 0L;
        if (membership != null) {
            total += membership.getDiscountAmount();
        }
        if (coupon != null) {
            total += coupon.getDiscountAmount();
        }
        return total;
    }
}
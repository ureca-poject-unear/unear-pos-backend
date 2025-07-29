package com.unear.pos.common.dto;

import com.unear.pos.common.dto.enums.MembershipGrade;
import com.unear.pos.discount.dto.response.DiscountApplyResponseDto;
import com.unear.pos.member.dto.MemberInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class MemberSession {
    private Long memberId;
    private MembershipGrade memberGrade;
    private Long placeId;
    private String memberName;

    private DiscountApplyResponseDto membershipDiscount;
    private DiscountApplyResponseDto couponDiscount;
    private Long totalDiscountAmount;
    private Long originalAmount;

    private Long userCouponId;
    private String discountCode;

    public static MemberSession from(MemberInfo memberInfo, PosSessionInfo posInfo) {
        return MemberSession.builder()
                .memberId(memberInfo.getMemberId())
                .memberGrade(memberInfo.getMemberGrade())
                .placeId(posInfo.getPlaceId())
                .memberName(memberInfo.getMemberName())
                .totalDiscountAmount(0L)
                .build();
    }

    public MemberSession withMembershipDiscount(DiscountApplyResponseDto discount) {
        Long baseAmount = determineOriginalAmount(discount);
        return this.toBuilder()
                .membershipDiscount(discount)
                .totalDiscountAmount(calculateTotal(discount, this.couponDiscount))
                .originalAmount(baseAmount)
                .discountCode(discount.getDiscountCode())
                .build();
    }

    public MemberSession withCouponDiscount(DiscountApplyResponseDto discount, Long userCouponId) {
        Long baseAmount = determineOriginalAmount(discount);
        return this.toBuilder()
                .couponDiscount(discount)
                .userCouponId(userCouponId)
                .totalDiscountAmount(calculateTotal(this.membershipDiscount, discount))
                .originalAmount(baseAmount)
                .discountCode(discount.getDiscountCode())
                .build();
    }

    private Long determineOriginalAmount(DiscountApplyResponseDto discount) {
        return this.originalAmount != null
                ? this.originalAmount
                : discount.getFinalAmount() + discount.getDiscountAmount();
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

    public boolean hasCouponApplied() {
        return this.userCouponId != null;
    }
}
package com.unear.pos.common.util;

import com.unear.pos.common.dto.MemberSession;
import com.unear.pos.discount.dto.response.DiscountApplyResponseDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class MemberSessionUtil {
    private static final String MEMBER_SESSION_KEY = "memberSession";


    public MemberSession validateAndGetMemberSession(HttpSession session) {
        MemberSession memberSession = (MemberSession) session.getAttribute(MEMBER_SESSION_KEY);
        if (memberSession == null) {
            throw new IllegalStateException("회원 인증이 필요합니다");
        }
        return memberSession;
    }

    public void saveMembershipDiscount(HttpSession session, DiscountApplyResponseDto discount) {
        MemberSession current = validateAndGetMemberSession(session);
        MemberSession updated = current.withMembershipDiscount(discount);
        session.setAttribute(MEMBER_SESSION_KEY, updated);
    }

    public void saveCouponDiscount(HttpSession session, DiscountApplyResponseDto discount, Long userCouponId) {
        MemberSession current = validateAndGetMemberSession(session);
        MemberSession updated = current.withCouponDiscount(discount, userCouponId);
        session.setAttribute(MEMBER_SESSION_KEY, updated);
    }

    public void clearMemberSession(HttpSession session) {
        session.removeAttribute(MEMBER_SESSION_KEY);
    }
}

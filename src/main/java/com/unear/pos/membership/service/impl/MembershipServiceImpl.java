package com.unear.pos.membership.service.impl;

import com.unear.pos.common.dto.MemberSession;
import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.dto.enums.VerificationType;
import com.unear.pos.common.exception.business.MemberNotFoundException;
import com.unear.pos.discount.dto.DiscountPolicyInfo;
import com.unear.pos.discount.service.DiscountService;
import com.unear.pos.member.dto.MemberInfo;
import com.unear.pos.member.entity.Member;
import com.unear.pos.membership.dto.MemberVerifyRequestDto;
import com.unear.pos.membership.repository.MemberRepository;
import com.unear.pos.membership.service.MembershipService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MemberRepository memberRepository;
    private final DiscountService discountService;


    @Override
    public MemberInfo verifyMember(MemberVerifyRequestDto request, PosSessionInfo posInfo, HttpSession session) {
        VerificationType type = VerificationType.fromString(request.getType());

        Member member = switch (type) {
            case BARCODE -> findByBarcode(request.getValue());
            case PHONE -> findByPhone(request.getValue());
        };

        MemberInfo memberInfo = MemberInfo.from(member);

        MemberSession memberSession = MemberSession.from(memberInfo, posInfo, request.getPurchaseAmount());
        session.setAttribute("memberSession", memberSession);

        List<DiscountPolicyInfo> policies = discountService.getDiscountPolicies(memberInfo.getMemberGrade(), posInfo);

        return memberInfo.withDiscountPolicies(policies);
    }

    private Member findByPhone(String value) {
        return memberRepository.findByTel(value)
                .orElseThrow(() -> new MemberNotFoundException("전화번호로 회원을 찾을 수 없습니다"));
    }

    private Member findByBarcode(String value) {
        return memberRepository.findByBarcodeNumber(value)
                .orElseThrow(() -> new MemberNotFoundException("바코드로 회원을 찾을 수 없습니다"));
    }
}

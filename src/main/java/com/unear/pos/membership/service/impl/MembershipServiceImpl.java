package com.unear.pos.membership.service.impl;

import com.unear.pos.common.dto.enums.VerificationType;
import com.unear.pos.common.exception.business.MemberNotFoundException;
import com.unear.pos.member.dto.MemberInfo;
import com.unear.pos.member.entity.Member;
import com.unear.pos.membership.dto.MemberVerifyRequestDto;
import com.unear.pos.membership.repository.MemberRepository;
import com.unear.pos.membership.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MemberRepository memberRepository;


    @Override
    public MemberInfo verifyMember(MemberVerifyRequestDto request) {
        VerificationType type = VerificationType.fromString(request.getType());

        Member member = switch (type) {
            case BARCODE -> findByBarcode(request.getValue());
            case PHONE -> findByPhone(request.getValue());
        };

        return MemberInfo.from(member);
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

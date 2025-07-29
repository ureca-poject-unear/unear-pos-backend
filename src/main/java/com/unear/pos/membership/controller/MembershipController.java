package com.unear.pos.membership.controller;

import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.resolver.CurrentPosSession;
import com.unear.pos.common.response.ApiResponse;
import com.unear.pos.discount.dto.request.DiscountApplyRequestDto;
import com.unear.pos.discount.dto.response.DiscountApplyResponseDto;
import com.unear.pos.discount.service.DiscountService;
import com.unear.pos.member.dto.MemberInfo;
import com.unear.pos.membership.dto.MemberVerifyRequestDto;
import com.unear.pos.membership.service.MembershipService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membership")
@RequiredArgsConstructor
@Slf4j
public class MembershipController {

    private final MembershipService membershipService;
    private final DiscountService discountService;

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<MemberInfo>> verifyMember(
            @Valid @RequestBody MemberVerifyRequestDto request,
            @CurrentPosSession PosSessionInfo posInfo,
            HttpSession session) {

        MemberInfo memberInfo = membershipService.verifyMember(request, posInfo, session);
        return ResponseEntity.ok(ApiResponse.success("회원 인증 완료", memberInfo));
    }

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<DiscountApplyResponseDto>> applyDiscount(
            @Valid @RequestBody DiscountApplyRequestDto request,
            @CurrentPosSession PosSessionInfo posInfo,
            HttpSession session) {

        DiscountApplyResponseDto response = discountService.applyMembershipDiscount(
                request, posInfo, session);

        return ResponseEntity.ok(ApiResponse.success("할인 적용 완료", response));
    }

    @PostMapping("/cancel")
    public ResponseEntity<ApiResponse<Void>> cancelMembershipDiscount(@CurrentPosSession PosSessionInfo posInfo,
                                                                      HttpSession session) {
        membershipService.cancelMembershipDiscount(session);
        return ResponseEntity.ok(ApiResponse.success("멤버십 할인이 취소되었습니다"));
    }
}

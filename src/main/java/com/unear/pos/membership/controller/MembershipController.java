package com.unear.pos.membership.controller;

import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.resolver.CurrentPosSession;
import com.unear.pos.common.response.ApiResponse;
import com.unear.pos.member.dto.MemberInfo;
import com.unear.pos.membership.dto.MemberVerifyRequestDto;
import com.unear.pos.membership.service.MembershipService;
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

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<MemberInfo>> verifyMember(
            @Valid @RequestBody MemberVerifyRequestDto request,
            @CurrentPosSession PosSessionInfo posInfo) {

        MemberInfo memberInfo = membershipService.verifyMember(request);
        return ResponseEntity.ok(ApiResponse.success("회원 인증 완료", memberInfo));
    }
}

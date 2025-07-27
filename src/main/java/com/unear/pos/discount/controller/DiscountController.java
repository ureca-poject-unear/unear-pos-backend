package com.unear.pos.discount.controller;

import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.resolver.CurrentPosSession;
import com.unear.pos.common.response.ApiResponse;
import com.unear.pos.discount.dto.request.DiscountApplyRequestDto;
import com.unear.pos.discount.dto.response.DiscountApplyResponseDto;
import com.unear.pos.discount.service.DiscountService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<DiscountApplyResponseDto>> applyDiscount(
            @Valid @RequestBody DiscountApplyRequestDto request,
            @CurrentPosSession PosSessionInfo posInfo,
            HttpSession session) {

        DiscountApplyResponseDto response = discountService.applyDiscount(
                request, posInfo, session);

        return ResponseEntity.ok(ApiResponse.success("할인 적용 완료", response));
    }
}
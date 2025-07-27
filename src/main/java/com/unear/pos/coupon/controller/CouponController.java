package com.unear.pos.coupon.controller;

import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.resolver.CurrentPosSession;
import com.unear.pos.common.response.ApiResponse;
import com.unear.pos.coupon.dto.request.CouponApplyRequestDto;
import com.unear.pos.coupon.dto.request.CouponVerifyRequestDto;
import com.unear.pos.coupon.dto.response.CouponVerifyResponseDto;
import com.unear.pos.coupon.service.CouponService;
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
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;
    private final DiscountService discountService;

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<CouponVerifyResponseDto>> verifyCoupon(
            @Valid @RequestBody CouponVerifyRequestDto request,
            @CurrentPosSession PosSessionInfo posInfo,
            HttpSession session) {

        CouponVerifyResponseDto response = couponService.verifyCoupon(request, posInfo, session);

        return ResponseEntity.ok(ApiResponse.success("쿠폰 검증 완료", response));
    }

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<DiscountApplyResponseDto>> applyCouponDiscount(
            @Valid @RequestBody CouponApplyRequestDto request,
            @CurrentPosSession PosSessionInfo posInfo,
            HttpSession session) {

        DiscountApplyResponseDto response = discountService.applyCouponDiscount(request, posInfo, session);

        return ResponseEntity.ok(ApiResponse.success("쿠폰 할인 적용 완료", response));
    }
}

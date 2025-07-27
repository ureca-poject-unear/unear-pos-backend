package com.unear.pos.payment.controller;

import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.resolver.CurrentPosSession;
import com.unear.pos.common.response.ApiResponse;
import com.unear.pos.payment.dto.request.PaymentRequestDto;
import com.unear.pos.payment.dto.response.PaymentResponseDto;
import com.unear.pos.payment.entity.UserHistory;
import com.unear.pos.payment.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<ApiResponse<PaymentResponseDto>> processPayment(
            @Valid @RequestBody PaymentRequestDto request,
            @CurrentPosSession PosSessionInfo posInfo,
            HttpSession session) {

        PaymentResponseDto response = paymentService.processPayment(request, session, posInfo);

        return ResponseEntity.ok(ApiResponse.success("결제 완료", response));
    }

    @GetMapping("/history/place/{placeId}")
    public ResponseEntity<ApiResponse<List<UserHistory>>> getPlacePaymentHistory(
            @PathVariable Long placeId) {

        List<UserHistory> history = paymentService.getPlacePaymentHistory(placeId);

        return ResponseEntity.ok(ApiResponse.success("가게 결제 내역 조회 완료", history));
    }
}
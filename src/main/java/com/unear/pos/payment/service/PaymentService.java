package com.unear.pos.payment.service;

import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.payment.dto.request.PaymentRequestDto;
import com.unear.pos.payment.dto.response.PaymentResponseDto;
import com.unear.pos.payment.entity.UserHistory;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public interface PaymentService {

    PaymentResponseDto processPayment(PaymentRequestDto request, HttpSession session, PosSessionInfo posInfo);

    List<UserHistory> getPlacePaymentHistory(Long placeId);
}
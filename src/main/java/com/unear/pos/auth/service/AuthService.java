package com.unear.pos.auth.service;

import com.unear.pos.auth.dto.request.LoginRequestDto;
import com.unear.pos.common.response.ApiResponse;
import jakarta.servlet.http.HttpSession;

public interface AuthService {

    ApiResponse<Void> login(LoginRequestDto loginRequestDto, HttpSession session);
}

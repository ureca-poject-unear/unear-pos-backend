package com.unear.pos.auth.controller;

import com.unear.pos.auth.dto.request.LoginRequestDto;
import com.unear.pos.auth.service.AuthService;
import com.unear.pos.common.response.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Void>> login(@RequestBody LoginRequestDto loginRequestDto, HttpSession session) {
        ApiResponse<Void> response = authService.login(loginRequestDto, session);

        return ResponseEntity.ok(response);
    }
}

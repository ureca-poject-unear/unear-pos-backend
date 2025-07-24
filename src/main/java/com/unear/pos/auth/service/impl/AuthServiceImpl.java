package com.unear.pos.auth.service.impl;

import com.unear.pos.auth.dto.request.LoginRequestDto;
import com.unear.pos.auth.service.AuthService;
import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.response.ApiResponse;
import com.unear.pos.owner.entity.Owner;
import com.unear.pos.owner.repository.OwnerRepository;
import com.unear.pos.place.entity.Place;
import com.unear.pos.place.repository.PlaceRepository;
import com.unear.pos.pos.entity.Pos;
import com.unear.pos.pos.repository.PosRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;
    private final PosRepository posRepository;
    private final PlaceRepository placeRepository;

    @Override
    public ApiResponse<Void> login(LoginRequestDto loginRequestDto, HttpSession session) {

        Owner owner = ownerRepository.findByOwnerName(loginRequestDto.getOwnerName())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        if (!owner.getOwnerPassword().equals(loginRequestDto.getOwnerPassword())) {
            throw new RuntimeException("Passwords don't match");
        }

        Pos pos = posRepository.findById(owner.getPosId()).orElseThrow(() -> new RuntimeException("Pos not found"));

        Place place = placeRepository.findById(pos.getPlaceId())
                .orElseThrow(() -> new RuntimeException("Place not found"));

        PosSessionInfo posSessionInfo = PosSessionInfo.from(owner, place);

        session.setAttribute("posSessionInfo", posSessionInfo);

        return ApiResponse.success("login success");

    }
}

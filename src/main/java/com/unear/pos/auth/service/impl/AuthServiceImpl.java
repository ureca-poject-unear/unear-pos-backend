package com.unear.pos.auth.service.impl;

import com.unear.pos.auth.dto.request.LoginRequestDto;
import com.unear.pos.auth.service.AuthService;
import com.unear.pos.common.dto.PosSessionInfo;
import com.unear.pos.common.exception.business.EntityNotFoundException;
import com.unear.pos.common.exception.business.OwnerNotFoundException;
import com.unear.pos.common.response.ApiResponse;
import com.unear.pos.common.security.CustomOwnerUser;
import com.unear.pos.owner.entity.Owner;
import com.unear.pos.owner.repository.OwnerRepository;
import com.unear.pos.place.entity.Place;
import com.unear.pos.place.repository.PlaceRepository;
import com.unear.pos.pos.entity.Pos;
import com.unear.pos.pos.repository.PosRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final OwnerRepository ownerRepository;
    private final PosRepository posRepository;
    private final PlaceRepository placeRepository;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<Void> login(LoginRequestDto loginRequestDto, HttpSession session) {

        Owner owner = ownerRepository.findByOwnerName(loginRequestDto.getOwnerName())
                .orElseThrow(() -> new OwnerNotFoundException("사용자를 찾을 수 없습니다"));

        if (!owner.getOwnerPassword().equals(loginRequestDto.getOwnerPassword())) {
            throw new OwnerNotFoundException("사용자를 찾을 수 없습니다");
        }

        Pos pos = posRepository.findById(owner.getPosId())
                .orElseThrow(() -> new EntityNotFoundException("Pos", owner.getPosId()));

        Place place = placeRepository.findById(pos.getPlaceId())
                .orElseThrow(() -> new EntityNotFoundException("Place", pos.getPlaceId()));

        CustomOwnerUser userDetails = CustomOwnerUser.from(owner);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);

        PosSessionInfo posSessionInfo = PosSessionInfo.from(owner, place);

        session.setAttribute("posSessionInfo", posSessionInfo);

        return ApiResponse.success("login success");

    }
}

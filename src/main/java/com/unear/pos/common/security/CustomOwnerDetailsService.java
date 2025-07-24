package com.unear.pos.common.security;

import com.unear.pos.owner.entity.Owner;
import com.unear.pos.owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOwnerDetailsService implements UserDetailsService {

    private final OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByOwnerName(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 오너를 찾을 수 없습니다."));
        return new CustomOwnerUser(owner);
    }

    public CustomOwnerUser loadUserByOwnerId(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new UsernameNotFoundException("오너를 찾을 수 없습니다: " + ownerId));
        return new CustomOwnerUser(owner);
    }
}
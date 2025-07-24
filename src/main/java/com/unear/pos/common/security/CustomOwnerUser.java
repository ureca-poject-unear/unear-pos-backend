package com.unear.pos.common.security;

import com.unear.pos.owner.entity.Owner;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomOwnerUser implements UserDetails {

    private final Owner owner;

    public CustomOwnerUser(Owner owner) {
        this.owner = owner;
    }

    public Long getId() {
        return owner.getOwnerId();
    }

    public Long getPosId() {
        return owner.getPosId();
    }

    public String getOwnerName() {
        return owner.getOwnerName();
    }

    public Owner getOwner() {
        return owner;
    }

    public static CustomOwnerUser from(Owner owner) {
        return new CustomOwnerUser(owner);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // 권한 분기 안 하므로 비워둠
    }

    @Override
    public String getPassword() {
        return owner.getOwnerPassword();
    }

    @Override
    public String getUsername() {
        return owner.getOwnerName(); // 로그인 ID 필드
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.love.lylph.security;

import com.love.lylph.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 潘淮  on 2018/8/16.<br>
 */
public class SecurityDto extends User implements UserDetails {
    private static final long serialVersionUID = 500L;

    public SecurityDto(User user) {
        this.setName(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEnable(user.isEnable());
        this.setRoleName(user.getRoleName());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(this.getRoleName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
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
        return super.isEnable();
    }
}

package com.shopping.application.user.serviceimpl;

import com.shopping.application.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsGroup implements UserDetails {
    private String username;
    private String password;
    private boolean isActive = true;
    private List<GrantedAuthority> roles;

    public UserDetailsGroup(User user) {
        String roles = "";
        if(user.isAdmin()) roles = "ROLE_ADMIN,ROLE_CUSTOMER";
        else roles = "ROLE_CUSTOMER";
        this.roles = Arrays.stream(roles.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        this.username = user.getEmail();
        this.password = user.getPassword();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return isActive;
    }
}

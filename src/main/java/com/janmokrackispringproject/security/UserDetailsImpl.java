package com.janmokrackispringproject.security;

import com.janmokrackispringproject.beans.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        for(SimpleGrantedAuthority role : Arrays.asList(new
                SimpleGrantedAuthority(user.getRole().name()))){
            System.out.println(role.getAuthority());
        }
        return Arrays.asList(new
                SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        System.out.println("pass "+this.user.getPass());
        return this.user.getPass();
    }

    @Override
    public String getUsername() {
        System.out.println("username "+this.user.getLogin());
        return this.user.getLogin();
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

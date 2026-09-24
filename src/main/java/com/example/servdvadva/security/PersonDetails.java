package com.example.servdvadva.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class PersonDetail implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of();
    }
}

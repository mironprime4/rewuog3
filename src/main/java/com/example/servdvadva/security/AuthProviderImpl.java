package com.example.servdvadva.security;

import com.example.servdvadva.service.PersonDetailService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthProviderImpl implements AuthenticationProvider {
    private final PersonDetailService personDetailService;


    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetailsService = personDetailService.loadUserByUsername(username);

        if (!password.equals(userDetailsService.getPassword()))
            throw new BadCredentialsException("IncorrectPassword");
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}

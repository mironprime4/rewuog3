package com.example.servdvadva.service;


import com.example.servdvadva.entity.Person;
import com.example.servdvadva.entity.Role;
import com.example.servdvadva.repository.PeopleRepository;
import com.example.servdvadva.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class RegisterService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    private final JWTUtil jwtUtil;

    @Transactional
    public Map<String, String> register(Person person) {
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);
        person.setRole(Role.ROLE_STUDENT);

        String accessToken = jwtUtil.generateToken(person.getUsername());
        String refreshToken = jwtUtil.generateTokenRefresh(person.getUsername());

        person.setRefreshToken(refreshToken);
        person.setTokenExpiration(LocalDateTime.now().plusDays(60));

        peopleRepository.save(person);

        return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
    }

    @Transactional
    public Map<String, String> refreshToken(String oldRefreshToken) {
        Person person = peopleRepository.findByToken(oldRefreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if (person.getTokenExpiration().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Refresh Token has expired");
        }

        String newAccess = jwtUtil.generateToken(person.getUsername());
        String newRefresh = jwtUtil.generateTokenRefresh(person.getUsername());

        return Map.of("accessToken", newAccess, "refreshToken" , newRefresh);
    }

    @Transactional
    public Map<String, String> authentication(String username){
        Person person = peopleRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        String accessToken = jwtUtil.generateToken(username);
        String refreshToken = jwtUtil.generateTokenRefresh(username);

        person.setRefreshToken(refreshToken);
        person.setTokenExpiration(LocalDateTime.now().plusDays(60));
        return Map.of("accessToken", accessToken,"refreshToken", refreshToken);
    }
}

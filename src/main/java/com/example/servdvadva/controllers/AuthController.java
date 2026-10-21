package com.example.servdvadva.controllers;


import com.example.servdvadva.dto.AuthDTO;
import com.example.servdvadva.dto.PersonDto;
import com.example.servdvadva.entity.Person;
import com.example.servdvadva.service.RegisterService;
import com.example.servdvadva.util.JWTUtil;
import com.example.servdvadva.util.PersonValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private RegisterService registerService;
    private PersonValidator personValidator;

    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Map<String, String> loginPage(@RequestBody AuthDTO authDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword());
        try {
         authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect Credential!");
        }
        return registerService.authentication(authDTO.getUsername());
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDto personDto, BindingResult bindingResult) {
        Person person = convertToPerson(personDto);
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) return Map.of("ошибка", "Ошибка регистрации");
        return registerService.register(person);
    }

    @PostMapping("/refresh")
    public Map<String, String> refresh(@RequestBody Map<String, String> body){
        String refreshToken = body.get("refreshToken");
        if (refreshToken == null) return Map.of("message", "Missini refresh token");

        return registerService.refreshToken(refreshToken);
    }

    public Person convertToPerson(PersonDto person) {
        return this.modelMapper.map(person, Person.class);
    }
}

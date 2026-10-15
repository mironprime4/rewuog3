package com.example.servdvadva.controllers;


import com.example.servdvadva.dto.PersonDto;
import com.example.servdvadva.entity.Person;
import com.example.servdvadva.service.RegisterService;
import com.example.servdvadva.util.JWTUtil;
import com.example.servdvadva.util.PersonValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
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
        registerService.register(person);

        String token = jwtUtil.generateToken(person.getUsername());
        return Map.of("jwt-token", token);
    }

    public Person  convertToPerson(PersonDto person){
        return this.modelMapper.map(person, Person.class);
    }
}

package com.example.servdvadva.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthDTO {
    @NotEmpty
    @Size(min = 2, max = 100)
    private String username;
    private String password;
}

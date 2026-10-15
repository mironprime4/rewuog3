package com.example.servdvadva.dto;

import com.example.servdvadva.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class PersonDto {
    @NotEmpty
    @Size(min = 2, max = 100)
    private String username;

    @Min(value = 1900)
    @Column(name = "yser_of_birth")
    private Integer yearOfBirth;

    private String password;
}

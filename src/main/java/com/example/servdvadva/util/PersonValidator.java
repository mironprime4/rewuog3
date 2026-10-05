package com.example.servdvadva.util;


import com.example.servdvadva.entity.Person;
import com.example.servdvadva.service.PersonDetailService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Component
public class PersonValidator implements Validator {
    private PersonDetailService personDetailService;


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        try {
            personDetailService.loadUserByUsername(person.getUsername());
        } catch (UsernameNotFoundException ignored){
            return;
        }

        errors.rejectValue("username", "", "user already exists");
    }
}

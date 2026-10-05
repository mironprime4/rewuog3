package com.example.servdvadva.service;


import com.example.servdvadva.entity.Person;
import com.example.servdvadva.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegisterService {
    private final PeopleRepository peopleRepository;

    @Transactional
    public void register(Person person){
        peopleRepository.save(person);
    }
}

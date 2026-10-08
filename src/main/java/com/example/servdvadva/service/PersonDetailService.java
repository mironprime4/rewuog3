package com.example.servdvadva.service;


import com.example.servdvadva.repository.PeopleRepository;
import com.example.servdvadva.entity.Person;
import com.example.servdvadva.security.PersonDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonDetailService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person =  peopleRepository.findByUsername(username);
        if (person.isEmpty()){
            throw new UsernameNotFoundException("user nor found");
        }
        return new PersonDetails(person.get());
    }
}

package com.example.servdvadva.controllers;


import com.example.servdvadva.entity.Person;
import com.example.servdvadva.entity.StudentSubject;
import com.example.servdvadva.repository.StudentSubjectRepository;
import com.example.servdvadva.security.PersonDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class StudentSubjectController {

    private final StudentSubjectRepository subjectRepository;

    @GetMapping("/main")
    public List<StudentSubject> getByName(Authentication authentication){
        PersonDetails person = (PersonDetails) authentication.getPrincipal();
        int studentId = person.getPerson().getId();
        return subjectRepository.findByStudent(studentId);
    }
}

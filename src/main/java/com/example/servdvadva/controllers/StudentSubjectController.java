package com.example.servdvadva.controllers;


import com.example.servdvadva.entity.Person;
import com.example.servdvadva.entity.StudentSubject;
import com.example.servdvadva.entity.Task;
import com.example.servdvadva.repository.StudentSubjectRepository;
import com.example.servdvadva.repository.TaskRepository;
import com.example.servdvadva.security.PersonDetails;
import jakarta.persistence.metamodel.ListAttribute;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class StudentSubjectController {

    private final TaskRepository taskRepository;
    private final StudentSubjectRepository subjectRepository;

    @GetMapping("/main")
    public List<StudentSubject> getByName(Authentication authentication){
        PersonDetails person = (PersonDetails) authentication.getPrincipal();
        int studentId = person.getPerson().getId();
        return subjectRepository.findByStudent(studentId);
    }

    @GetMapping("/task/{subject_id}")
    public List<Task> getTasks(@PathVariable int subject_id){
        List<Task> tasks = taskRepository.findTaskBySubjectId(subject_id);
        return tasks;
    }

    @GetMapping("/task/{subject_id}/{task_name}")
    public List<Task> getTaskByName(@PathVariable int subject_id, @PathVariable String task_name){
        List<Task> tasks = taskRepository.findByNameContainingAndSubjectName(subject_id, task_name);
        return tasks;
    }
}

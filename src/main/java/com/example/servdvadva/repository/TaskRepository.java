package com.example.servdvadva.repository;

import com.example.servdvadva.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findTaskBySubjectId(int subject_id);
    List<Task>  findByNameContainingAndSubjectName(int subject_id, String name);
}

package com.example.servdvadva.repository;

import com.example.servdvadva.entity.Student;
import com.example.servdvadva.entity.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Integer> {
    @Query(value =
            "SELECT ss.* FROM student_subject ss INNER JOIN subjects s ON ss.subject_id = s.id WHERE ss.student_id = :studentId", nativeQuery = true)
    List<StudentSubject> findByStudent(@Param("studentId") int studentId);
}

package com.example.servdvadva.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student_task")
public class StudentTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private int sid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private int tid;


}

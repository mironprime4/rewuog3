package com.example.servdvadva.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private int sid;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private int qid;
}

package com.reglus.backend.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "interests_hobbies")
public class InterestHobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hobbyId;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(length = 255)
    private String activitiesOutsideSchool;

    @Column(length = 500)
    private String dreamsGoals;
}

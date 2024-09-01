package com.reglus.backend.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "study_habits")
public class StudyHabit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyId;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(length = 100)
    private String studyMethods;

    @Enumerated(EnumType.STRING)
    private StudyHoursPerDay studyHoursPerDay;

    @Column(length = 100)
    private String studyLocations;

    @Column(length = 255)
    private String studyPlan;
}

enum StudyHoursPerDay {
    MENOS_DE_1_HORA, UMA_DUAS_HORAS, DUAS_TRES_HORAS, MAIS_DE_3_HORAS
}

package com.reglus.backend.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "health_wellbeing")
public class HealthWellbeing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long healthId;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(length = 255)
    private String healthCondition;

    @Column(length = 255)
    private String physicalActivity;

    @Enumerated(EnumType.STRING)
    private DietaryEvaluation dietaryEvaluation;

    @Enumerated(EnumType.STRING)
    private SleepHours sleepHours;
}

enum DietaryEvaluation {
    MUITO_SAUDAVEL, SAUDAVEL, REGULAR, POUCO_SAUDAVEL, NADA_SAUDAVEL
}

enum SleepHours {
    MENOS_DE_6_HORAS, SEIS_SETE_HORAS, SETE_OITO_HORAS, OITO_NOVE_HORAS, MAIS_DE_9_HORAS
}
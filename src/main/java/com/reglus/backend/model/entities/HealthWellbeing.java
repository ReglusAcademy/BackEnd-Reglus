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

    // Getters and Setters
    public Long getHealthId() {
        return healthId;
    }

    public void setHealthId(Long healthId) {
        this.healthId = healthId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getPhysicalActivity() {
        return physicalActivity;
    }

    public void setPhysicalActivity(String physicalActivity) {
        this.physicalActivity = physicalActivity;
    }

    public DietaryEvaluation getDietaryEvaluation() {
        return dietaryEvaluation;
    }

    public void setDietaryEvaluation(DietaryEvaluation dietaryEvaluation) {
        this.dietaryEvaluation = dietaryEvaluation;
    }

    public SleepHours getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(SleepHours sleepHours) {
        this.sleepHours = sleepHours;
    }
}



enum DietaryEvaluation {
    MUITO_SAUDAVEL, SAUDAVEL, REGULAR, POUCO_SAUDAVEL, NADA_SAUDAVEL
}

enum SleepHours {
    MENOS_DE_6_HORAS, SEIS_SETE_HORAS, SETE_OITO_HORAS, OITO_NOVE_HORAS, MAIS_DE_9_HORAS
}
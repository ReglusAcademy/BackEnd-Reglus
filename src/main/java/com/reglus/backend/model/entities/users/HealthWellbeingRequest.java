package com.reglus.backend.model.entities.users;

import com.reglus.backend.model.enums.HealthDietaryEvaluation;
import com.reglus.backend.model.enums.HealthSleepHours;

public class HealthWellbeingRequest {
    private Long studentId; // Relacionamento com o estudante
    private String healthCondition; // Condição de saúde
    private String physicalActivity; // Atividades físicas
    private HealthDietaryEvaluation dietaryEvaluation; // Avaliação dietética
    private HealthSleepHours sleepHours; // Horas de sono

    // Getters e Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public HealthDietaryEvaluation getDietaryEvaluation() {
        return dietaryEvaluation;
    }

    public void setDietaryEvaluation(HealthDietaryEvaluation dietaryEvaluation) {
        this.dietaryEvaluation = dietaryEvaluation;
    }

    public HealthSleepHours getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(HealthSleepHours sleepHours) {
        this.sleepHours = sleepHours;
    }
}
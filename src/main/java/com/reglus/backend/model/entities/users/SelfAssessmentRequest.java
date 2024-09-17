package com.reglus.backend.model.entities.users;
import com.reglus.backend.model.enums.SelfPerformanceEvaluation;

public class SelfAssessmentRequest {
    private Long studentId;
    private SelfPerformanceEvaluation performanceEvaluation;
    private String strengths;
    private String improvementAreas;

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public SelfPerformanceEvaluation getPerformanceEvaluation() {
        return performanceEvaluation;
    }

    public void setPerformanceEvaluation(SelfPerformanceEvaluation performanceEvaluation) {
        this.performanceEvaluation = performanceEvaluation;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getImprovementAreas() {
        return improvementAreas;
    }

    public void setImprovementAreas(String improvementAreas) {
        this.improvementAreas = improvementAreas;
    }
}


package com.reglus.backend.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "self_assessment")
public class SelfAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assessmentId;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Enumerated(EnumType.STRING)
    private PerformanceEvaluation performanceEvaluation;

    @Column(length = 255)
    private String strengths;

    @Column(length = 255)
    private String improvementAreas;
}

enum PerformanceEvaluation {
    MUITO_BOA, BOA, REGULAR, RUIM, MUITO_RUIM
}

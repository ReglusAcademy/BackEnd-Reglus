package com.reglus.backend.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "social_aspects")
public class SocialAspect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socialId;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(length = 255)
    private String livingWith;

    @Enumerated(EnumType.STRING)
    private Relationship relationshipWithClassmates;

    @Enumerated(EnumType.STRING)
    private Relationship relationshipWithTeachers;

    @Enumerated(EnumType.STRING)
    private Relationship relationshipWithFamily;
}

enum Relationship {
    MUITO_BOA, BOA, REGULAR, RUIM, MUITO_RUIM
}
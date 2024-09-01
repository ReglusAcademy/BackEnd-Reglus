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

    // Getters and Setters
    public Long getSocialId() {
        return socialId;
    }

    public void setSocialId(Long socialId) {
        this.socialId = socialId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getLivingWith() {
        return livingWith;
    }

    public void setLivingWith(String livingWith) {
        this.livingWith = livingWith;
    }

    public Relationship getRelationshipWithClassmates() {
        return relationshipWithClassmates;
    }

    public void setRelationshipWithClassmates(Relationship relationshipWithClassmates) {
        this.relationshipWithClassmates = relationshipWithClassmates;
    }

    public Relationship getRelationshipWithTeachers() {
        return relationshipWithTeachers;
    }

    public void setRelationshipWithTeachers(Relationship relationshipWithTeachers) {
        this.relationshipWithTeachers = relationshipWithTeachers;
    }

    public Relationship getRelationshipWithFamily() {
        return relationshipWithFamily;
    }

    public void setRelationshipWithFamily(Relationship relationshipWithFamily) {
        this.relationshipWithFamily = relationshipWithFamily;
    }
}

enum Relationship {
    MUITO_BOA, BOA, REGULAR, RUIM, MUITO_RUIM
}
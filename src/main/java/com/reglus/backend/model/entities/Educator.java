package com.reglus.backend.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "educators")
public class Educator extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "educator_id")
    private Long educatorId;

    @Column(name = "bio", length = 500)
    private String bio;

    @Column(name = "experience_years")
    private Integer experienceYears;

    public Educator() {
        super(); // Construtor vazio
    }

    public Educator(UserType userType, String email, String passwordHash, String gender, String disability, String educationLevel, String instituteName) {
        super(userType, email, passwordHash, gender, disability, educationLevel, instituteName);
    }

    // getters e setters
    public Long getEducatorId() {
        return educatorId;
    }

    public void setEducatorId(Long educatorId) {
        this.educatorId = educatorId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }
}
package com.reglus.backend.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "social_id")
    private SocialAspect socialAspect;

    @ManyToOne
    @JoinColumn(name = "health_id")
    private HealthWellbeing healthWellbeing;

    @ManyToOne
    @JoinColumn(name = "hobbies_id")
    private InterestHobby interestHobby;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private StudyHabit studyHabit;

    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private SelfAssessment selfAssessment;

    @Column(length = 500)
    private String finalObservations;

    @Column(length = 50)
    private String state;

    @Column(length = 50)
    private String city;

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SocialAspect getSocialAspect() {
        return socialAspect;
    }

    public void setSocialAspect(SocialAspect socialAspect) {
        this.socialAspect = socialAspect;
    }

    public HealthWellbeing getHealthWellbeing() {
        return healthWellbeing;
    }

    public void setHealthWellbeing(HealthWellbeing healthWellbeing) {
        this.healthWellbeing = healthWellbeing;
    }

    public InterestHobby getInterestHobby() {
        return interestHobby;
    }

    public void setInterestHobby(InterestHobby interestHobby) {
        this.interestHobby = interestHobby;
    }

    public StudyHabit getStudyHabit() {
        return studyHabit;
    }

    public void setStudyHabit(StudyHabit studyHabit) {
        this.studyHabit = studyHabit;
    }

    public SelfAssessment getSelfAssessment() {
        return selfAssessment;
    }

    public void setSelfAssessment(SelfAssessment selfAssessment) {
        this.selfAssessment = selfAssessment;
    }

    public String getFinalObservations() {
        return finalObservations;
    }

    public void setFinalObservations(String finalObservations) {
        this.finalObservations = finalObservations;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
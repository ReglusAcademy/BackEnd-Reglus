package com.reglus.backend.model.entities.users;

public class InterestHobbyRequest {
    private Long studentId;
    private String activitiesOutsideSchool;
    private String dreamsGoals;

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getActivitiesOutsideSchool() {
        return activitiesOutsideSchool;
    }

    public void setActivitiesOutsideSchool(String activitiesOutsideSchool) {
        this.activitiesOutsideSchool = activitiesOutsideSchool;
    }

    public String getDreamsGoals() {
        return dreamsGoals;
    }

    public void setDreamsGoals(String dreamsGoals) {
        this.dreamsGoals = dreamsGoals;
    }
}

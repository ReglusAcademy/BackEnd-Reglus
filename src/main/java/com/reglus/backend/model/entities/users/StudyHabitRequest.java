package com.reglus.backend.model.entities.users;
import com.reglus.backend.model.enums.StudyHoursPerDay;

public class StudyHabitRequest {
    private Long studentId;
    private String studyMethods;
    private StudyHoursPerDay studyHoursPerDay;
    private String studyLocations;
    private String studyPlan;

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudyMethods() {
        return studyMethods;
    }

    public void setStudyMethods(String studyMethods) {
        this.studyMethods = studyMethods;
    }

    public StudyHoursPerDay getStudyHoursPerDay() {
        return studyHoursPerDay;
    }

    public void setStudyHoursPerDay(StudyHoursPerDay studyHoursPerDay) {
        this.studyHoursPerDay = studyHoursPerDay;
    }

    public String getStudyLocations() {
        return studyLocations;
    }

    public void setStudyLocations(String studyLocations) {
        this.studyLocations = studyLocations;
    }

    public String getStudyPlan() {
        return studyPlan;
    }

    public void setStudyPlan(String studyPlan) {
        this.studyPlan = studyPlan;
    }
}

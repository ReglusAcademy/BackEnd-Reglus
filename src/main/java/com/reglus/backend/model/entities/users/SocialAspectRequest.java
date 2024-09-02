package com.reglus.backend.model.entities.users;

import com.reglus.backend.model.enums.SocialRelationship;

public class SocialAspectRequest {
    private Long studentId;
    private String livingWith;
    private SocialRelationship relationshipWithClassmates;
    private SocialRelationship relationshipWithTeachers;
    private SocialRelationship relationshipWithFamily;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getLivingWith() {
        return livingWith;
    }

    public void setLivingWith(String livingWith) {
        this.livingWith = livingWith;
    }

    public SocialRelationship getRelationshipWithClassmates() {
        return relationshipWithClassmates;
    }

    public void setRelationshipWithClassmates(SocialRelationship relationshipWithClassmates) {
        this.relationshipWithClassmates = relationshipWithClassmates;
    }

    public SocialRelationship getRelationshipWithTeachers() {
        return relationshipWithTeachers;
    }

    public void setRelationshipWithTeachers(SocialRelationship relationshipWithTeachers) {
        this.relationshipWithTeachers = relationshipWithTeachers;
    }

    public SocialRelationship getRelationshipWithFamily() {
        return relationshipWithFamily;
    }

    public void setRelationshipWithFamily(SocialRelationship relationshipWithFamily) {
        this.relationshipWithFamily = relationshipWithFamily;
    }
}

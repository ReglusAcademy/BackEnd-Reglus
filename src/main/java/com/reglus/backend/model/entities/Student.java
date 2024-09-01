package com.reglus.backend.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "final_observations", length = 500)
    private String finalObservations;

    // Outras colunas e mapeamentos de relacionamento

    public Student() {
        super(); // Construtor vazio
    }

    public Student(UserType userType, String email, String passwordHash, String gender, String disability, String educationLevel, String instituteName) {
        super(userType, email, passwordHash, gender, disability, educationLevel, instituteName);
    }

    // Getters e Setters

}

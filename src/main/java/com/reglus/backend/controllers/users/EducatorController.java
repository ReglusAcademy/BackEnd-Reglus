package com.reglus.backend.controllers.users;

import com.reglus.backend.model.entities.users.User;
import com.reglus.backend.model.entities.users.Educator;
import com.reglus.backend.model.enums.UserType;
import com.reglus.backend.repositories.EducatorRepository;
import com.reglus.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/educators")
public class EducatorController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EducatorRepository educatorRepository;


    // Create a new educator
    @PostMapping
    public ResponseEntity<?> createEducator(@RequestBody EducatorRequest educatorRequest) {
        try {
            // Create User
            User user = new User();
            user.setUserType(educatorRequest.getUserType());
            user.setEmail(educatorRequest.getEmail());
            user.setPasswordHash(educatorRequest.getPasswordHash());
            user.setName(educatorRequest.getName());
            user.setDateBirth(educatorRequest.getDateBirth());
            user.setGender(educatorRequest.getGender());
            user.setDisability(educatorRequest.getDisability());
            user.setEducationLevel(educatorRequest.getEducationLevel());
            user.setInstituteName(educatorRequest.getInstituteName());
            userRepository.save(user);

            // Create Educator
            Educator educator = new Educator();
            educator.setUser(user);
            educator.setExperienceYears(educatorRequest.getExperienceYears());
            educator.setBio(educatorRequest.getBio());
            educatorRepository.save(educator);

            return new ResponseEntity<>(educator, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get all educators
    @GetMapping
    public ResponseEntity<List<Educator>> getAllEducators() {
        try {
            List<Educator> educators = educatorRepository.findAll();
            if (educators.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(educators, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get educator by ID
    @GetMapping("/{id}")
    public ResponseEntity<Educator> getEducatorById(@PathVariable("id") Long id) {
        Optional<Educator> educatorData = educatorRepository.findById(id);

        if (educatorData.isPresent()) {
            return new ResponseEntity<>(educatorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update educator by ID
    @PutMapping("/{id}")
    public ResponseEntity<Educator> updateEducator(@PathVariable("id") Long id, @RequestBody EducatorRequest educatorRequest) {
        Optional<Educator> educatorData = educatorRepository.findById(id);

        if (educatorData.isPresent()) {
            Educator educator = educatorData.get();
            User user = educator.getUser();

            user.setUserType(educatorRequest.getUserType());
            user.setEmail(educatorRequest.getEmail());
            user.setPasswordHash(educatorRequest.getPasswordHash());
            user.setName(educatorRequest.getName());
            user.setDateBirth(educatorRequest.getDateBirth());
            user.setGender(educatorRequest.getGender());
            user.setDisability(educatorRequest.getDisability());
            user.setEducationLevel(educatorRequest.getEducationLevel());
            user.setInstituteName(educatorRequest.getInstituteName());
            userRepository.save(user);

            educator.setExperienceYears(educatorRequest.getExperienceYears());
            educator.setBio(educatorRequest.getBio());
            educatorRepository.save(educator);

            return new ResponseEntity<>(educator, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete educator and corresponding user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEducator(@PathVariable("id") Long id) {
        try {
            Optional<Educator> educatorData = educatorRepository.findById(id);

            if (educatorData.isPresent()) {
                Educator educator = educatorData.get();
                User user = educator.getUser();

                // Delete educator first
                educatorRepository.deleteById(id);

                // Delete corresponding user
                userRepository.delete(user);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Request DTO
    public static class EducatorRequest {
        private UserType userType;
        private String email;
        private String passwordHash;
        private String name;
        private LocalDate dateBirth;
        private String gender;
        private String disability;
        private String educationLevel;
        private String instituteName;
        private String experienceYears;
        private String bio;

        // Getters and Setters
        public UserType getUserType() {
            return userType;
        }

        public void setUserType(UserType userType) {
            this.userType = userType;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDate getDateBirth() {
            return dateBirth;
        }

        public void setDateBirth(LocalDate dateBirth) {
            this.dateBirth = dateBirth;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDisability() {
            return disability;
        }

        public void setDisability(String disability) {
            this.disability = disability;
        }

        public String getEducationLevel() {
            return educationLevel;
        }

        public void setEducationLevel(String educationLevel) {
            this.educationLevel = educationLevel;
        }

        public String getInstituteName() {
            return instituteName;
        }

        public void setInstituteName(String instituteName) {
            this.instituteName = instituteName;
        }

        public String getExperienceYears() {
            return experienceYears;
        }

        public void setExperienceYears(String experienceYears) {
            this.experienceYears = experienceYears;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }
    }
}

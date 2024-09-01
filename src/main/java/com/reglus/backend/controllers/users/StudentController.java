package com.reglus.backend.controllers.users;

import com.reglus.backend.model.entities.users.Student;
import com.reglus.backend.model.entities.users.User;
import com.reglus.backend.model.entities.users.StudentRequest;
import com.reglus.backend.model.enums.UserType;
import com.reglus.backend.repositories.StudentRepository;
import com.reglus.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentRequest studentRequest) {
        try {
            User user = new User();
            user.setUserType(UserType.STUDENT);
            user.setEmail(studentRequest.getEmail());
            user.setPasswordHash(studentRequest.getPasswordHash());
            user.setName(studentRequest.getName());
            user.setDateBirth(studentRequest.getDateBirth());
            user.setGender(studentRequest.getGender());
            user.setDisability(studentRequest.getDisability());
            user.setEducationLevel(studentRequest.getEducationLevel());
            user.setInstituteName(studentRequest.getInstituteName());
            userRepository.save(user);

            Student student = new Student();
            student.setUser(user);
            student.setState(studentRequest.getState());
            student.setCity(studentRequest.getCity());
            student.setFinalObservations(studentRequest.getFinalObservations());
            studentRepository.save(student);

            return new ResponseEntity<>(student, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentRepository.findAll();
            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Optional<Student> studentData = studentRepository.findById(id);

        if (studentData.isPresent()) {
            return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        try {
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (!optionalStudent.isPresent()) {
                return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
            }

            Student student = optionalStudent.get();

            Optional<User> optionalUser = userRepository.findById(student.getUser().getUserId());
            if (!optionalUser.isPresent()) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            User user = optionalUser.get();

            user.setEmail(studentRequest.getEmail());
            user.setPasswordHash(studentRequest.getPasswordHash());
            user.setName(studentRequest.getName());
            user.setDateBirth(studentRequest.getDateBirth());
            user.setGender(studentRequest.getGender());
            user.setDisability(studentRequest.getDisability());
            user.setEducationLevel(studentRequest.getEducationLevel());
            user.setInstituteName(studentRequest.getInstituteName());
            userRepository.save(user);

            student.setUser(user);
            student.setState(studentRequest.getState());
            student.setCity(studentRequest.getCity());
            student.setFinalObservations(studentRequest.getFinalObservations());
            studentRepository.save(student);

            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Long id) {
        try {
            Optional<Student> studentData = studentRepository.findById(id);

            if (studentData.isPresent()) {
                Student student = studentData.get();
                User user = student.getUser();

                studentRepository.deleteById(id);
                userRepository.delete(user);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

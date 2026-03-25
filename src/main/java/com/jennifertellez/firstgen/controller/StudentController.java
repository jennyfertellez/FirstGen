package com.jennifertellez.firstgen.controller;

import com.jennifertellez.firstgen.exception.StudentNotFoundException;
import com.jennifertellez.firstgen.model.Student;
import com.jennifertellez.firstgen.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // POST /students - create a new student
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // GET /students - return all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // GET /students/{id} - return one student by id
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    // PUT /students/{id} - update an existing student, return the updated object
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setGender(updatedStudent.getGender());
        existingStudent.setCulturalIdentity(updatedStudent.getCulturalIdentity());
        existingStudent.setResidencyStatus(updatedStudent.getResidencyStatus());
        existingStudent.setResidencyStatusNote(updatedStudent.getResidencyStatusNote());
        existingStudent.setAcademicLevel(updatedStudent.getAcademicLevel());
        existingStudent.setCurrentSchool(updatedStudent.getCurrentSchool());
        existingStudent.setTargetSchool(updatedStudent.getTargetSchool());
        existingStudent.setAcademicYear(updatedStudent.getAcademicYear());
        existingStudent.setMajor(updatedStudent.getMajor());

        return studentRepository.save(existingStudent);
    }

    // DELETE /students/{id} - delete a student, return 204 No Content
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentById(@PathVariable Long id) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(existingStudent);
    }
}

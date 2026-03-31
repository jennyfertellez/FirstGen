package com.jennifertellez.firstgen.service;

import com.jennifertellez.firstgen.exception.StudentNotFoundException;
import com.jennifertellez.firstgen.model.Student;
import com.jennifertellez.firstgen.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        if (updatedStudent.getEmail() != null) {
            existingStudent.setEmail(updatedStudent.getEmail());
        }

        if (updatedStudent.getResidencyStatus() != null) {
            existingStudent.setResidencyStatus(updatedStudent.getResidencyStatus());
        }

        if (updatedStudent.getResidencyStatusNote() != null) {
            existingStudent.setResidencyStatusNote(updatedStudent.getResidencyStatusNote());
        }

        if (updatedStudent.getAcademicLevel() != null) {
            existingStudent.setAcademicLevel(updatedStudent.getAcademicLevel());
        }

        if (updatedStudent.getCurrentSchool() != null) {
            existingStudent.setCurrentSchool(updatedStudent.getCurrentSchool());
        }

        if (updatedStudent.getTargetSchool() != null) {
            existingStudent.setTargetSchool(updatedStudent.getTargetSchool());
        }

        if (updatedStudent.getMajor() != null) {
            existingStudent.setMajor(updatedStudent.getMajor());
        }

        return studentRepository.save(existingStudent);
    }

    public void deleteStudentById(Long id) {
        Student existingStudent = studentRepository.findById(id)
                        .orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(existingStudent);
    }

    public boolean isHighPriority(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        // Logic: If students is attending a community college and have > 45 units
        boolean isAtCommunityCollege = student.getCurrentSchool().toLowerCase().contains("college");
        boolean isCloseToTransfer = student.getTotalUnits() != null && student.getTotalUnits() >= 45;

        return isAtCommunityCollege && isCloseToTransfer;
    }
}

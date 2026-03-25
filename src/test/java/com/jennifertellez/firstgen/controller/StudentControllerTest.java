package com.jennifertellez.firstgen.controller;

import com.jennifertellez.firstgen.model.Student;
import com.jennifertellez.firstgen.model.enums.AcademicLevel;
import com.jennifertellez.firstgen.model.enums.CulturalIdentity;
import com.jennifertellez.firstgen.model.enums.Gender;
import com.jennifertellez.firstgen.model.enums.ResidencyStatus;
import com.jennifertellez.firstgen.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    void createStudent_HappyPath() throws Exception {
        Student student = buildStudent("Jennifer", "Flores");

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Jennifer"))
                .andExpect(jsonPath("$.email").value("jennifer@firstgen.dev"));
    }

    @Test
    void createStudent_withMultipleCulturalIdentities_savesAll() throws Exception {
        Student student = buildStudent("Maria", "Lopez");
        student.setCulturalIdentity(List.of(
                CulturalIdentity.MEXICAN_OR_CHICANO,
                CulturalIdentity.NATIVE_AMERICAN_OR_ALASKA_NATIVE));

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.culturalIdentity.length()").value(2));

    }

    @Test
    void createStudent_withOtherResidencyStatus_savesNote() throws Exception {
        Student student = buildStudent("Ana", "Garcia");
        student.setResidencyStatus(ResidencyStatus.OTHER);
        student.setResidencyStatusNote("TPS Holder");

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.residencyStatus").value("OTHER"))
                .andExpect(jsonPath("$.residencyStatusNote").value("TPS Holder"));
    }

    @Test
    void getAllStudents_returnsEmptyList() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

    }

    @Test
    void getAllStudents_returnsAllStudents() throws Exception {
        studentRepository.save(buildStudent("Jennifer", "Flores"));
        studentRepository.save(buildStudent("Maria", "Lopez"));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getStudentById_returnsStudent() throws Exception {
        Student saved = studentRepository.save(
                buildStudent("Jennifer", "Flores"));

        mockMvc.perform(get("/students/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jennifer"));
    }

    @Test
    void getStudentById_returns404() throws Exception {
        mockMvc.perform(get("/students/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateStudent_validId_returnsUpdatedStudent() throws Exception {
        Student saved = studentRepository.save(buildStudent("Jennifer", "Flores"));

        Student updated = buildStudent("Jennifer", "Flores");
        updated.setMajor("Biology");
        updated.setTargetSchool("UCLA");

        mockMvc.perform(put("/students/" + saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.major").value("Biology"))
                .andExpect(jsonPath("$.targetSchool").value("UCLA"));
    }

    @Test
    void updateStudent_invalidId_returns404() throws Exception {
        Student updated = buildStudent("Jennifer", "Flores");

        mockMvc.perform(put("/students/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteStudent_validId_returns204() throws Exception {
        Student saved = studentRepository.save(buildStudent("Jennifer", "Flores"));

        mockMvc.perform(delete("/students/" + saved.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteStudent_invalidId_returns404() throws Exception {
        mockMvc.perform(delete("/students/9999"))
                .andExpect(status().isNotFound());
    }

    private Student buildStudent(String firstName, String lastName) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail("jennifer@firstgen.dev");
        student.setGender(Gender.WOMAN);
        student.setCulturalIdentity(List.of(CulturalIdentity.MEXICAN_OR_CHICANO));
        student.setResidencyStatus(ResidencyStatus.CITIZEN);
        student.setAcademicLevel(AcademicLevel.COLLEGE_SOPHOMORE);
        student.setCurrentSchool("Mesa College");
        student.setTargetSchool("UC San Diego");
        student.setAcademicYear(2024);
        student.setMajor("Computer Science");
        return student;
    }

}
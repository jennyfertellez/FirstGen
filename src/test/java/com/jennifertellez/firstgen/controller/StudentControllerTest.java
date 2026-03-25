package com.jennifertellez.firstgen.controller;

import com.jennifertellez.firstgen.model.Student;
import com.jennifertellez.firstgen.model.ImmigrationStatus;
import com.jennifertellez.firstgen.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        Student student = buildStudent("Jennifer", "Flores", ImmigrationStatus.CITIZEN, null);

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Jennifer"))
                .andExpect(jsonPath("$.email").value("jennifer@firstgen.dev"));
    }

    @Test
    void createStudent_WithOtherStatus_savesNote()  throws Exception {
        Student student = buildStudent("Maria", "Lopez", ImmigrationStatus.OTHER, "TPS holder");

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.immigrationStatus").value("OTHER"))
                .andExpect(jsonPath("$.immigrationStatusNote").value("TPS holder"));
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
        studentRepository.save(buildStudent("Jennifer", "Flores", ImmigrationStatus.CITIZEN, null));
        studentRepository.save(buildStudent("Maria", "Lopez", ImmigrationStatus.DACA, null));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getStudentById_returnsStudent() throws Exception {
        Student saved = studentRepository.save(
                buildStudent("Jennifer", "Flores", ImmigrationStatus.CITIZEN, null));

        mockMvc.perform(get("/students/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jennifer"));
    }

    @Test
    void getStudentById_returns404() throws Exception {
        mockMvc.perform(get("/students/9999"))
                .andExpect(status().isNotFound());
    }

    private Student buildStudent(String firstName, String lastName,
                                 ImmigrationStatus immigrationStatus, String note) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail("jennifer@firstgen.dev");
        student.setCurrentSchool("Mesa College");
        student.setTargetSchool("UC San Diego");
        student.setEnrollmentYear(2024);
        student.setMajor("Computer Science");
        student.setImmigrationStatus(immigrationStatus);
        student.setImmigrationStatusNote(note);
        return student;
    }

}
package com.jennifertellez.firstgen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String currentSchool;
    private String targetSchool;
    private Integer enrollmentYear;
    private String major;

    @Enumerated(EnumType.STRING)
    private ImmigrationStatus immigrationStatus;

    // Free-text for statuses not covered by the enum
    private String immigrationStatusNote;

    public Student() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCurrentSchool() { return currentSchool; }
    public void setCurrentSchool(String currentSchool) { this.currentSchool = currentSchool; }

    public String getTargetSchool() { return targetSchool; }
    public void setTargetSchool(String targetSchool) { this.targetSchool = targetSchool; }

    public Integer getEnrollmentYear() { return enrollmentYear; }
    public void setEnrollmentYear(Integer enrollmentYear) { this.enrollmentYear = enrollmentYear; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public ImmigrationStatus getImmigrationStatus() { return immigrationStatus; }
    public void setImmigrationStatus(ImmigrationStatus immigrationStatus) {
        this.immigrationStatus = immigrationStatus;
    }

    public String getImmigrationStatusNote() { return immigrationStatusNote; }
    public void setImmigrationStatusNote(String immigrationStatusNote) {
        this.immigrationStatusNote = immigrationStatusNote;
    }
}

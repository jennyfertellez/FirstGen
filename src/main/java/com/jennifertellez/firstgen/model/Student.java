package com.jennifertellez.firstgen.model;

import com.jennifertellez.firstgen.model.enums.AcademicLevel;
import com.jennifertellez.firstgen.model.enums.CulturalIdentity;
import com.jennifertellez.firstgen.model.enums.Gender;
import com.jennifertellez.firstgen.model.enums.ResidencyStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ElementCollection
    @CollectionTable(name = "student_cultural_identities",
    joinColumns = @JoinColumn(name = "student_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "cultural_identity")
    private List<CulturalIdentity> culturalIdentity;

    @Enumerated(EnumType.STRING)
    private ResidencyStatus residencyStatus;

    private String residencyStatusNote;

    @Enumerated(EnumType.STRING)
    private AcademicLevel academicLevel;

    private String currentSchool;
    private String targetSchool;
    private Integer academicYear;
    private String major;


    public Student() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public List<CulturalIdentity> getCulturalIdentity() { return culturalIdentity; }
    public void setCulturalIdentity(List<CulturalIdentity> culturalIdentity) {
        this.culturalIdentity = culturalIdentity;
    }

    public ResidencyStatus getResidencyStatus() { return residencyStatus; }
    public void setResidencyStatus(ResidencyStatus residencyStatus) {
        this.residencyStatus = residencyStatus;
    }

    public String getResidencyStatusNote() { return residencyStatusNote; }
    public void setResidencyStatusNote(String residencyStatusNote) {
        this.residencyStatusNote = residencyStatusNote;
    }

    public AcademicLevel getAcademicLevel() { return academicLevel; }
    public void setAcademicLevel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
    }

    public String getCurrentSchool() { return currentSchool; }
    public void setCurrentSchool(String currentSchool) { this.currentSchool = currentSchool; }

    public String getTargetSchool() { return targetSchool; }
    public void setTargetSchool(String targetSchool) { this.targetSchool = targetSchool; }

    public Integer getAcademicYear() { return academicYear; }
    public void setAcademicYear(Integer academicYear) { this.academicYear = academicYear; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

}

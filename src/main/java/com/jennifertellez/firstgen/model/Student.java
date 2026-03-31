package com.jennifertellez.firstgen.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jennifertellez.firstgen.model.enums.AcademicLevel;
import com.jennifertellez.firstgen.model.enums.CulturalIdentity;
import com.jennifertellez.firstgen.model.enums.Gender;
import com.jennifertellez.firstgen.model.enums.ResidencyStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    private Integer totalUnits = 0;

}

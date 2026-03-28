package com.jennifertellez.firstgen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transfer_paths")
public class TransferPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "target_school_id")
    private School targetSchool;

    private String major;

    private Integer catalogYear;

    public TransferPath() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public School getTargetSchool() { return targetSchool; }
    public void setTargetSchool(School targetSchool) { this.targetSchool = targetSchool; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public Integer getCatalogYear() { return catalogYear; }
    public void setCatalogYear(Integer catalogYear) { this.catalogYear = catalogYear; }
}

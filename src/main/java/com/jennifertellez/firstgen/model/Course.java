package com.jennifertellez.firstgen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;
    private String courseName;
    private Double units;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    public Course() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Double getUnits() { return units; }
    public void setUnits(Double units) { this.units = units; }

    public School getSchool() { return school; }
    public void setSchool(School school) { this.school = school; }
}

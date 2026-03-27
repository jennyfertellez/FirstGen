package com.jennifertellez.firstgen.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "articulation_groups")
public class ArticulationGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The target school
    @ManyToOne
    @JoinColumn(name = "target_school_id")
    private School targetSchool;

    // The major this articulation applies to
    private String major;

    // The course being satisfied
    @ManyToOne
    @JoinColumn(name = "target_course_id")
    private Course targetCourse;

    // The CC course(s) that satisfy it
    @ManyToMany
    @JoinTable(
            name = "articulation_cc_courses",
            joinColumns = @JoinColumn(name = "articulation_group_id"),
            inverseJoinColumns =  @JoinColumn(name = "course_id")
    )
    private List<Course> ccCourses;

    public ArticulationGroup() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public School getTargetSchool() { return targetSchool; }
    public void setTargetSchool(School targetSchool) { this.targetSchool = targetSchool; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public Course getTargetCourse() { return targetCourse; }
    public void setTargetCourse(Course targetCourse) { this.targetCourse = targetCourse; }

    public List<Course> getCcCourses() { return ccCourses; }
    public void setCcCourses(List<Course> ccCourses) {  this.ccCourses = ccCourses; }
}

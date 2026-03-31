package com.jennifertellez.firstgen.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "articulation_groups")
@Data
@NoArgsConstructor
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

    private boolean isRequired;
}

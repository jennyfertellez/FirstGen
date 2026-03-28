package com.jennifertellez.firstgen.model;

import com.jennifertellez.firstgen.model.enums.Semester;
import jakarta.persistence.*;

@Entity
@Table(name = "completed_courses")
public class CompletedCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String grade;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private Semester semester;

    private Integer yearTaken;

    public CompletedCourse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public Semester getSemester() { return semester; }
    public void setSemester(Semester semester) { this.semester = semester; }

    public Integer getYearTaken() { return yearTaken; }
    public void setYearTaken(Integer yearTaken) { this.yearTaken = yearTaken; }
}

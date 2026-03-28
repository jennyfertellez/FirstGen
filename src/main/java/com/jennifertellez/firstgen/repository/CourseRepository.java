package com.jennifertellez.firstgen.repository;

import com.jennifertellez.firstgen.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

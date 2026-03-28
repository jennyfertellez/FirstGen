package com.jennifertellez.firstgen.repository;

import com.jennifertellez.firstgen.model.CompletedCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedCourseRepository extends JpaRepository<CompletedCourse,Long> {
}

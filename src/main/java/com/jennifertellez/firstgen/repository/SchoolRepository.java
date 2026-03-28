package com.jennifertellez.firstgen.repository;

import com.jennifertellez.firstgen.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}

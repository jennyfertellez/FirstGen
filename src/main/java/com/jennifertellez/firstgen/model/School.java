package com.jennifertellez.firstgen.model;

import com.jennifertellez.firstgen.model.enums.SchoolType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schools")
@Data
@NoArgsConstructor
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SchoolType schoolType;

    private String city;
    private String state;
}

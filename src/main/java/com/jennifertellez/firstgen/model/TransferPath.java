package com.jennifertellez.firstgen.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transfer_paths")
@Data
@NoArgsConstructor
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
}

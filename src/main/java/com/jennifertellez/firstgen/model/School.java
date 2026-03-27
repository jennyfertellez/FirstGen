package com.jennifertellez.firstgen.model;

import com.jennifertellez.firstgen.model.enums.SchoolType;
import jakarta.persistence.*;

@Entity
@Table(name = "schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SchoolType schoolType;

    private String city;
    private String state;

    public School() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public SchoolType getSchoolType() { return schoolType; }
    public void setSchoolType(SchoolType schoolType) { this.schoolType = schoolType; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}

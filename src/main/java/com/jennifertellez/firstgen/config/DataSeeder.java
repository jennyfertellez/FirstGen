package com.jennifertellez.firstgen.config;

import com.jennifertellez.firstgen.model.ArticulationGroup;
import com.jennifertellez.firstgen.model.Course;
import com.jennifertellez.firstgen.model.School;
import com.jennifertellez.firstgen.model.enums.SchoolType;
import com.jennifertellez.firstgen.repository.ArticulationGroupRepository;
import com.jennifertellez.firstgen.repository.CourseRepository;
import com.jennifertellez.firstgen.repository.SchoolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final SchoolRepository schoolRepository;
    private final CourseRepository courseRepository;
    private final ArticulationGroupRepository articulationGroupRepository;

    public DataSeeder(SchoolRepository schoolRepository,
                      CourseRepository courseRepository,
                      ArticulationGroupRepository articulationGroupRepository) {
        this.schoolRepository = schoolRepository;
        this.courseRepository = courseRepository;
        this.articulationGroupRepository = articulationGroupRepository;
    }

    @Override
    public void run(String... args) {

        // --- Schools ---
        School mesa = new School();
        mesa.setName("San Diego Mesa College");
        mesa.setSchoolType(SchoolType.COMMUNITY_COLLEGE);
        mesa.setCity("San Diego");
        mesa.setState("CA");
        schoolRepository.save(mesa);

        School sdsu = new School();
        sdsu.setName("San Diego State University");
        sdsu.setSchoolType(SchoolType.CSU);
        sdsu.setCity("San Diego");
        sdsu.setState("CA");
        schoolRepository.save(sdsu);

        School ucsd =  new School();
        ucsd.setName("University of California, San Diego");
        ucsd.setSchoolType(SchoolType.UC);
        ucsd.setCity("San Diego");
        ucsd.setState("CA");
        schoolRepository.save(ucsd);

        // --- Mesa CC Courses ---
        Course cisc190 = saveCourse("CISC 190", "Introduction to Programming in Java", 4.0, mesa);
        Course cisc191 = saveCourse("CISC 191", "Intermediate Java Programming", 4.0, mesa);
        Course cisc192 = saveCourse("CISC 192", "C and C++ Programming", 4.0, mesa);
        Course cisc258 = saveCourse("CISC 258", "Assembly Language", 3.0, mesa);
        Course math141 = saveCourse("MATH 141", "Calculus I", 5.0, mesa);
        Course math142 = saveCourse("MATH 142", "Calculus II", 5.0, mesa);
        Course math245 = saveCourse("MATH 245", "Discrete Mathematics", 3.0, mesa);
        Course math254 = saveCourse("MATH 254", "Calculus III", 5.0, mesa);
        Course math255 = saveCourse("MATH 255", "Differential Equations", 3.0, mesa);
        Course math260 = saveCourse("MATH 260", "Linear Algebra", 3.0, mesa);
        Course phys195 = saveCourse("PHYS 195", "Mechanics", 4.0, mesa);
        Course phys196 = saveCourse("PHYS 196", "Electricity and Magnetism", 4.0, mesa);

        // --- SDSU Target Courses ---
        Course sdsuCS150 = saveCourse("CS 150", "Computer Organization", 3.0, sdsu);
        Course sdsuCS160 = saveCourse("CS 160", "Intermediate Prog and Data Structures", 3.0, sdsu);
        Course sdsuMATH150 = saveCourse("MATH 150", "Calculus I", 4.0, sdsu);
        Course sdsuMATH151 = saveCourse("MATH 151", "Calculus II", 4.0, sdsu);
        Course sdsuMATH245 = saveCourse("MATH 245", "Discrete Mathematics", 3.0, sdsu);
        Course sdsuMATH254 = saveCourse("MATH 254", "Introduction to Linear Algebra", 3.0, sdsu);

        // --- UCSD Target Courses ---
        Course ucsdCSE8A = saveCourse("CSE 8A", "Introduction to Programming in Java", 4.0, ucsd);
        Course ucsdCSE8B = saveCourse("CSE 8B", "Introduction to Programming in Java II", 4.0, ucsd);
        Course ucsdCSE30 = saveCourse("CSE 30", "Computer Organization", 4.0, ucsd);
        Course ucsdMATH20A = saveCourse("MATH 20A", "Calculus for Science and Engineering I", 4.0, ucsd);
        Course ucsdMATH20B = saveCourse("MATH 20B", "Calculus for Science and Engineering II", 4.0, ucsd);
        Course ucsdMATH20C = saveCourse("MATH 20C", "Calculus and Analytic Geometry", 4.0, ucsd);
        Course ucsdMATH18 = saveCourse("MATH 18", "Linear Algebra", 4.0, ucsd);

        String major = "Computer Science";

        // --- SDSU Articulation Groups ---
        // CISC 190 + CISC 191 → CS 160 at SDSU
        saveArticulation(sdsu, major, sdsuCS160, List.of(cisc190, cisc191));
        // CISC 192 + CISC 258 → CS 150 at SDSU
        saveArticulation(sdsu, major, sdsuCS150, List.of(cisc192, cisc258));
        // MATH 141 → MATH 150 at SDSU
        saveArticulation(sdsu, major, sdsuMATH150, List.of(math141));
        // MATH 142 → MATH 151 at SDSU
        saveArticulation(sdsu, major, sdsuMATH151, List.of(math142));
        // MATH 245 → MATH 245 at SDSU
        saveArticulation(sdsu, major, sdsuMATH245, List.of(math245));
        // MATH 260 → MATH 254 at SDSU
        saveArticulation(sdsu, major, sdsuMATH254, List.of(math260));

        // --- UCSD Articulation Groups ---
        // CISC 190 → CSE 8A at UCSD
        saveArticulation(ucsd, major, ucsdCSE8A, List.of(cisc190));
        // CISC 191 → CSE 8B at UCSD
        saveArticulation(ucsd, major, ucsdCSE8B, List.of(cisc191));
        // CISC 192 + CISC 258 → CSE 30 at UCSD
        saveArticulation(ucsd, major, ucsdCSE30, List.of(cisc192, cisc258));
        // MATH 141 → MATH 20A at UCSD
        saveArticulation(ucsd, major, ucsdMATH20A, List.of(math141));
        // MATH 142 → MATH 20B at UCSD
        saveArticulation(ucsd, major, ucsdMATH20B, List.of(math142));
        // MATH 254 → MATH 20C at UCSD
        saveArticulation(ucsd, major, ucsdMATH20C, List.of(math254));
        // MATH 260 → MATH 18 at UCSD
        saveArticulation(ucsd, major, ucsdMATH18, List.of(math260));
    }

    private Course saveCourse(String code, String name, double units, School school) {
        Course course = new Course();
        course.setCourseCode(code);
        course.setCourseName(name);
        course.setUnits(BigDecimal.valueOf(units));
        course.setSchool(school);
        return courseRepository.save(course);
    }

    private void saveArticulation(School targetSchool, String major,
                                  Course targetCourse, List<Course> ccCourses) {
        ArticulationGroup articulationGroup = new ArticulationGroup();
        articulationGroup.setTargetSchool(targetSchool);
        articulationGroup.setMajor(major);
        articulationGroup.setTargetCourse(targetCourse);
        articulationGroup.setCcCourses(ccCourses);
        articulationGroupRepository.save(articulationGroup);
    }
}

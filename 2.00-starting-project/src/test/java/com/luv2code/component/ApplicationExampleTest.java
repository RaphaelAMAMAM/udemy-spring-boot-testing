package com.luv2code.component;

import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ApplicationExampleTest {

    private static int count = 0;

    @Value("${info.app.name}")
    private String appInfo;

    @Value("${info.app.description}")
    private String appDescription;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    void setUp() {
        CollegeStudent collegeStudent = new CollegeStudent();
        count = count + 1;
        System.out.println("Testing: " + appInfo + " which is " + appDescription +
                " Version: " + appVersion + ". Execution of test method " + count);

        student.setFirstname("Eric");
        student.setLastname("Roby");
        student.setEmailAddress("eric.roby@aaa.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 85.0, 76.50, 91.75)));
        student.setStudentGrades(studentGrades);
    }

    @Test
    void basicTest() {
    }

    @Test
    void Add_grade_results_for_student_grades() {
        assertThat(353.25).isEqualTo(studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()));
    }

    @Test
    void Add_grade_results_for_student_grades_assert_not_equal() {
        assertThat(0).isNotEqualTo(studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()));
    }

    @Test
    void Create_student_without_grades_init() {
        // Car prototype dans main et donc on peut l'instancier plusieurs fois
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        studentTwo.setFirstname("Chad");
        studentTwo.setLastname("Darby");
        studentTwo.setEmailAddress("blabla@gmail.com");

        assertThat(studentTwo.getFirstname()).isNotNull();
        assertThat(studentTwo.getLastname()).isNotNull();
        assertThat(studentTwo.getEmailAddress()).isNotNull();
        assertThat(studentGrades.checkNull(studentTwo.getStudentGrades())).isNull();

        CollegeStudent studentThree = context.getBean("collegeStudent", CollegeStudent.class);
        CollegeStudent studentFour = context.getBean("collegeStudent", CollegeStudent.class);

        assertThat(studentThree).isNotEqualTo(studentFour);
    }

    @Test
    void Find_grade_point_average() {
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        assertThat(student).isNotEqualTo(studentTwo);

        assertAll(
                () -> assertThat(studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults())).isEqualTo(353.25),
                () -> assertThat(studentGrades.findGradePointAverage(student.getStudentGrades().getMathGradeResults())).isEqualTo(88.31)
                // ...
        );

    }
}

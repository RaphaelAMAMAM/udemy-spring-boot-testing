package com.luv2code.component;

import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReflectionUtilsTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

    @BeforeEach
    public void studentBeforeEach(){
        studentOne.setFirstname("aaa");
        studentOne.setLastname("BBB");
        studentOne.setEmailAddress("ccc@ccc.com");
        studentOne.setStudentGrades(studentGrades);

        ReflectionTestUtils.setField(studentOne, "id", 1);
        ReflectionTestUtils.setField(studentOne, "studentGrades", new StudentGrades(new ArrayList<>(Arrays.asList(100.00, 50.00, 90.00))));

    }

    @Test
    void get_private_field() {
        assertThat(ReflectionTestUtils.getField(studentOne, "id")).isEqualTo(1);
    }

    @Test
    void invoke_private_method() {
        assertThat("aaa 1").isEqualTo(ReflectionTestUtils.invokeMethod(studentOne, "getFirstNameAndId"));
    }
}
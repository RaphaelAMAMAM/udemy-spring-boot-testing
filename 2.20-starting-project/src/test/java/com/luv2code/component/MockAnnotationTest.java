package com.luv2code.component;

import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MockAnnotationTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

    // Creation du Mock
    // @Mock
    @MockBean
    private ApplicationDao applicationDao;

    // @InjectMocks
    @Autowired
    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        studentOne.setFirstname("Firstname");
        studentOne.setLastname("LastName");
        studentOne.setEmailAddress("aaa@aaa.com");
        studentOne.setStudentGrades(studentGrades);
    }

    @Test
    void assertEqualsTestAddGrades() {
        when(applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()))
                .thenReturn(100.00);

        assertThat(100.00).isEqualTo(applicationService.addGradeResultsForSingleClass(
                studentOne.getStudentGrades().getMathGradeResults()));

        verify(applicationDao, times(1)).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
    }

    @Test
    void Find_GPA() {
        when(applicationDao.findGradePointAverage(studentGrades.getMathGradeResults()))
                .thenReturn(88.31);

        assertThat(88.31).isEqualTo(applicationService.findGradePointAverage(studentOne
                .getStudentGrades().getMathGradeResults()));
    }

    @Test
    void not_null() {
        when(applicationDao.checkNull(studentGrades.getMathGradeResults()))
                .thenReturn(true);

        //assertThat(applicationDao.checkNull(studentGrades.getMathGradeResults())).isNotNull();

        assertThat(applicationService.checkNull(studentOne.getStudentGrades()
                .getMathGradeResults())).isNotNull();
    }

    @Test
    void Throw_runtime_exception() {
        CollegeStudent nullStudent = (CollegeStudent) applicationContext.getBean("collegeStudent");

        when(applicationDao.checkNull(nullStudent)).thenThrow(new RuntimeException());

        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            applicationService.checkNull(nullStudent);
        });

        verify(applicationDao, times(1)).checkNull(nullStudent);


    }

    @Test
    void Multiple_stubbing() {
        CollegeStudent nullStudent = (CollegeStudent) applicationContext.getBean("collegeStudent");
        when(applicationDao.checkNull(nullStudent))
                .thenThrow(new RuntimeException())
                .thenReturn("Do not throw exception second time");

        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            applicationService.checkNull(nullStudent);
        });

        assertThat(applicationService.checkNull(nullStudent)).isEqualTo("Do not throw exception second time");

        verify(applicationDao, times(2)).checkNull(nullStudent);
    }
}

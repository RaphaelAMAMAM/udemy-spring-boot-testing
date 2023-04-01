package com.udemy.JUnitReview;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

@SpringBootTest
// remove les () de fin + remplace les _ par espace
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JUnitReviewApplicationTests {

    DemoUtils demoUtils;

    @BeforeEach
    void setUp() {
        demoUtils = new DemoUtils();
        System.out.println("before each");


    }

    @AfterEach
    void tearDown() {
        System.out.println("after each");

    }


    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");

    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll");
    }

    @Test
    void contextLoads() {
    }

    @Test
    void test_Equals_And_Not_Equals() {
        DemoUtils demoUtils = new DemoUtils();

        assertThat(6).isEqualTo(demoUtils.add(2, 4));
        assertThat(6).isNotEqualTo(demoUtils.add(2, 5));
    }

    @Test
    void test_Null_And_Not_Null() {
        DemoUtils demoUtils = new DemoUtils();
        String str1 = null;
        String str2 = "aaaa";

        assertThat(demoUtils.checkNull(str1)).isNull();
        assertThat(demoUtils.checkNull(str2)).isNotNull();
    }


    @Test
    void test_same_and_not_same() {
        String str = "luv2code";

        assertThat(demoUtils.getAcademy()).isEqualTo(demoUtils.getAcademyDuplicate());
        assertThat(demoUtils.getAcademy()).isNotEqualTo(str);
    }

    @Test
    void Test_true_false() {
        int gradeOne = 10;
        int gradeTwo = 5;

        assertThat(demoUtils.isGreater(gradeOne, gradeTwo)).isTrue();
        assertThat(demoUtils.isGreater(gradeTwo, gradeOne)).isFalse();

    }

    @Test
    void test_array_equals() {
        String[] stringArray = {"A", "B", "C"};
        assertThat(stringArray).isEqualTo(demoUtils.getFirstThreeLettersOfAlphabet());
    }

    @Test
    void test_list_equals() {
        List<String> theList = List.of("luv", "2", "code");
        assertThat(theList).isEqualTo(demoUtils.getAcademyInList());
    }

    @Test
    void test_exception() {

        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            demoUtils.throwException(-1);
        });
    }

}

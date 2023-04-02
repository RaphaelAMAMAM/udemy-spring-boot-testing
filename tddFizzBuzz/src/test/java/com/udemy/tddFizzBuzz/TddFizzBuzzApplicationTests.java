package com.udemy.tddFizzBuzz;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TddFizzBuzzApplicationTests {

	// If number is divisible by 3, print Fizz
	// If number is divisible by 5, print Buzz
	// If number is divisible by 3 and 5, print FizzBuzz
	// If number is NOT divisible by 3 or 5, then print the number


    @Test
    void Should_return_Fizz_when_divisible_by_3() {
//        fail("Not implemented yet");
        String expected = "Fizz";
        assertThat(expected).isEqualTo(FizzBuzz.compute(3));
    }

    @Test
    void Should_return_Buzz_when_divisible_by_5() {
        String expected = "Buzz";
        assertThat(expected).isEqualTo(FizzBuzz.compute(5));
    }

    @Test
    void Should_return_FizzBuzz_when_divisible_by_3_and_5() {
        String expected = "FizzBuzz";
        assertThat(expected).isEqualTo(FizzBuzz.compute(15));
    }

    @Test
    void Should_return_number_when_NOT_divisible_by_3_nor_5() {
        String expected = "1";
        assertThat(expected).isEqualTo(FizzBuzz.compute(1));
    }

    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvSource({
            "1, 1",
            "2, 2",
            "3, Fizz",
            "4, 4",
            "5, Buzz",
            "15, FizzBuzz"
    })
    void Test_values_Fizz_Buzz(int value, String expected){
        assertThat(expected).isEqualTo(FizzBuzz.compute(value));
    }

    @ParameterizedTest(name="value={0}, expected={1}")
    @CsvFileSource(resources = {"/small-test-data.csv", "/small-2-test-data.csv"})
    void Test_values_Fizz_Buzz_with_CSV_file(int value, String expected){
        assertThat(expected).isEqualTo(FizzBuzz.compute(value));
    }


}

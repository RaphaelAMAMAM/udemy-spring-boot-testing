package com.udemy.tddFizzBuzz;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
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
}

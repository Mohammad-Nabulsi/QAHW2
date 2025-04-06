package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.MethodOrderer;
import main.najah.code.Calculator;


@DisplayName("Calculator Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {
	@BeforeAll
	public static void beforeClass() {
		System.out.println("Calculator turned on");
	}
	@BeforeEach
	void createCalculator() {
		calc = new Calculator();
		System.out.println("Calculator clean and ready to use.");
	}
	
	@AfterEach
	void testFinished(TestInfo testInfo) {
		System.out.println(testInfo.getDisplayName() + " has been tested.... cleaning calculator");
	}
	
	@AfterAll
	public static void afterClass() {
		System.out.println("Calculator turned off");
	}
	
	//5th requirement of the homework is done life cycles in at least one test class
	
    Calculator calc;
	//As for the tests for valid/invalid inputs all inputs in the add case are valid since ...
    //input was not integer code will not be compiled in the first place so invalid test cases won't be needed
    
    @DisplayName("Test addition")
    @ParameterizedTest(name = "Addition: {0} + {1} = {2}") //A parameterized test =======================================> included
    @CsvSource({
		"1,2,3",
		"-1,1,0",
		"5, 0, 5",
		"0,0, 0"
	}) 
    @Order(1) //Test addition first
    void AdditionTester(int a, int b, int expectedSum) {
		assertEquals(expectedSum , calc.add(a,b),  a + b, "The sum of " + a + ", " + b + " should be " + expectedSum);
	}
    
    //======================================================================================Addition covered
    //Start testing division
    
    //Also all compiled input is accepted valid input in this case except for 0 (edge case) so we only need to test zero  
    
    @Test
    @Order(2)
    @DisplayName("Testing if exception after dividing by zero is working as intended")
    void dividingByZero() {
    	assertThrows(ArithmeticException.class, ()->calc.divide(1, 0));
    }
     
    //Now testing if division is working correctly
    //no need to test for dividing by zero here since the order of this test is after 
    @DisplayName("Test division")
    @ParameterizedTest(name = "Division: {0} / {1} = {2}")
    @CsvSource({
		"5,1,5",
		"5,2,2",//since all integers cases where result is rounded should be covered
		"-5, 2, -2",
		"0,5, 0" //  (0/X) will should always be 0
	})
    @Order(3)
    void divisionTester(int a, int b, int result) {
    	assertEquals(result, calc.divide(a, b));
    }
    
    //======================================================================================Division covered
    //Start testing factorial
    
    //Invalid input is anything lesser than 0 (Since any non-integer will not be compiled in first place
    @Test
    @Order(4)
    @DisplayName("Testing for exception when calculating factorial for lesser than zero")
    void factorialLesserThanZero() {
    	assertThrows(IllegalArgumentException.class, ()->calc.factorial(-5));
    }
    //Testing valid input outcomes
    @DisplayName("Test factorial")
    @ParameterizedTest(name = "Factorial: {0}! = {1}")
    @CsvSource({
 		"0,1",
 		"1,1",
 		"4, 24",
 		"6,720"
 	}) 
    @Order(5)
    void facorialTester(int a, int result) {
    	//Will not be filtering huge numbers out as invalid inputs
    	//since it was not stated in class implementation
    	//but maybe filtering anything that take more than 0.5s to calculate  is practical
    	int actualResult = calc.factorial(a);
    	assertEquals(result, actualResult);
    }
    
    //one intentionally failing test that is disabled also fulfilling this requirement
    @Test
    @Order(6)
    @DisplayName("A failing test that is disabled also a timeout test")
   @Disabled  //Disabled test
    void exampleForFailingTest() {
    	 assertTimeout(Duration.ofMillis(300), () -> { //a time out test
             calc.factorial(999999999);  // for example it takes more than 0.3s on my PC 1.5s exactly
         });
    }
    //To fix this test either filter out huge numbers or increase threshold time
    
}


//To summarize this test class has checked for all valid and invalid input, 
//used descriptive display names for all non parameterized tests, ...
//included more than one parameterized test, included one timeout test,
//used multiple asserts (throws and equals)
//checking the 3rd requirement for the calculator test class

//oredered test execution using @Order checking the 4th requirement

//also life cycles has been used  checking the 5th requirement

//also included one intentionally failing disabled test with a note on how to fix it checking the 6th requirement

//test coverage for the class itself covered had 100% coverage checking the 7th requirement for the calculator

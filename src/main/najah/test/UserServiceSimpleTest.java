package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Calculator;
import main.najah.code.UserService;

class UserServiceSimpleTest {

	UserService us;
	@BeforeEach
	void createNewUserService() {
		us = new UserService();
		System.out.println("New user service template.");
	}
	@AfterEach
	void logEndOfService() {
		System.out.println("Service ended.");
	}

	//Let's start with invalid inputs
	@ParameterizedTest(name = "Testing invalid email: {0}")
	@ValueSource(strings = {"test.com", "test@outlook", "test", "", "      "}) //Invalid inputs
	@DisplayName("Invalid emails test")
	void testInvalidEmails(String email) {
		assertFalse(us.isValidEmail(email));
	}
	//And Null values
	@Test
	@DisplayName("Null email test")
	void nullEmailTest() {
		assertFalse(us.isValidEmail(null));
	}
	//Since we tested email values containing only (.), only (@), neither, empty, or null value
	//Now we can test the email containing both
	@ParameterizedTest(name = "Testing valid email: {0}")
	@ValueSource(strings = {"test@sth.com", "test@outlook.sth", "@.", "test.out@com", "   @ ..  "}) //Considered Valid inputs
	@DisplayName("Valid emails test")
	void testValidEmails(String email) {
		assertTrue(us.isValidEmail(email));
	}

	//valid email test done==========================
	//let's test authentication====================================
	//let's start with case where user name isn't admin
	//one case needed since only admin is valid
	@Test
	@DisplayName("Username that is not admin")
	void authNotAdminTest() {
		String username = "ANYTHING";
		assertFalse(us.authenticate(username, "1234"));
	}
	//case where password isn't 1234
	//one case needed since only 1234 is valid
		@Test
		@DisplayName("Password that is not 1234")
		@Timeout(value = 400, unit = TimeUnit.MILLISECONDS)
		void authNot1234Test() throws InterruptedException {
			//Assume fetching actual password from server to compare
			Thread.sleep(200); //simulating delay
			String pass = "404";
			assertFalse(us.authenticate("admin", pass));
		}
	//case where user name is admin and password is 1234
		@Test
		@DisplayName("Valid auth")
		void validAuthTest() {
			assertTrue(us.authenticate("admin", "1234"));
		}
}

//In summary we checked for valid/invalid input, 	had descriptive display names, 		had multiple assertions, 		
//Had a timeout test, 		 a parameterized test fulfilling 3rd
//100% coverage for class fulfilling 7th
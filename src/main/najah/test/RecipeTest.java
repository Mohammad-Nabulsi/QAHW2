package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Recipe;
import main.najah.code.RecipeException;
import main.najah.code.UserService;

class RecipeTest {

	Recipe r;
	@BeforeEach
	void createNewUserService() {
		r = new Recipe();
		System.out.println("New recipe created.");
	}
	//No input needed then no valid input option
	 @Test
	 @DisplayName("Recipe constructor test")
	    void testConstructor() {
	    assertEquals("", r.getName());
	    assertEquals(0, r.getPrice());
	    assertEquals(0, r.getAmtCoffee());
	    assertEquals(0, r.getAmtMilk());
	    assertEquals(0, r.getAmtSugar());
	    assertEquals(0, r.getAmtChocolate());
	   }
	 
	 //testing chocolate getter before and after setting
	 @Test
	 @DisplayName("Chocolate getter test")
	 void chocolateGetterTest() throws RecipeException { //recipe exception cause the setter might cause it
		 assertEquals(0, r.getAmtChocolate());
		 r.setAmtChocolate("1");
		 assertEquals(1, r.getAmtChocolate());		 
	 }
	 //Chocolate getter tested
	 
	 //testing coffee getter before and after setting
	 @Test
	 @DisplayName("coffee getter test")
	 void coffeeGetterTest() throws RecipeException { //recipe exception cause the setter might cause it
		 assertEquals(0, r.getAmtCoffee());
		 r.setAmtCoffee("1");
		 assertEquals(1, r.getAmtCoffee());		 
	 }
	 //coffee getter tested
	 
	//testing Milk getter before and after setting
		 @Test
		 @DisplayName("milk getter test")
		 void milkGetterTest() throws RecipeException { //recipe exception cause the setter might cause it
			 assertEquals(0, r.getAmtMilk());
			 r.setAmtMilk("1");
			 assertEquals(1, r.getAmtMilk());		 
		 }
		 //milk getter tested

		//testing sugar getter before and after setting
		 @Test
		 @DisplayName("sugar getter test")
		 void sugarGetterTest() throws RecipeException { //recipe exception cause the setter might cause it
			 assertEquals(0, r.getAmtSugar());
			 r.setAmtSugar("1");
			 assertEquals(1, r.getAmtSugar());		 
		 }
		 //sugar getter tested
		 
		//testing name getter before and after setting
		 @Test
		 @DisplayName("name getter test")
		 void nameGetterTest() throws RecipeException { //recipe exception cause the setter might cause it
			 assertEquals("", r.getName());
			 r.setName("recipe");
			 assertEquals("recipe", r.getName());		 
		 }
		 //name getter tested
		 
		//testing price getter before and after setting
		 @Test
		 @DisplayName("price getter test")
		 void priceGetterTest() throws RecipeException { //recipe exception cause the setter might cause it
			 assertEquals(0, r.getPrice());
			 r.setPrice("1");
			 assertEquals(1, r.getPrice());		 
		 }
		 //price getter tested
		 
		 //Chocolate setter
		 //invalid case first
		 @ParameterizedTest(name = "Invalid chocolate input: \"{0}\"")
		 @ValueSource(strings = {"-3", "five", "2.5", "", "  ", "1e2", "null"})
		 @DisplayName("Invalid chocolate inputs")
		 void chocolateSetterInvalidTest(String amt) {
		        assertThrows(RecipeException.class, () -> r.setAmtChocolate(amt));
		    }
		 //valid case now
		 @Test
		 @DisplayName("Valid chocolate input")
		 void chocolateSetterValidTest() throws RecipeException {
			 String amt = "10";
			 r.setAmtChocolate(amt);
			 assertEquals(10, r.getAmtChocolate());
		 }
		 //Chocolate setter tested ======================================================================================
		 
		//Coffee setter
		 //invalid case first
		 @ParameterizedTest(name = "Invalid coffee input: \"{0}\"")
		 @ValueSource(strings = {"-3", "five", "2.5", "", "  ", "1e2", "null"})
		 @DisplayName("Invalid coffee inputs")
		 void coffeeSetterInvalidTest(String amt) {
		        assertThrows(RecipeException.class, () -> r.setAmtCoffee(amt));
		    }
		 //valid case now
		 @Test
		 @DisplayName("Valid coffee input")
		 void coffeeSetterValidTest() throws RecipeException {
			 String amt = "10";
			 r.setAmtCoffee(amt);
			 assertEquals(10, r.getAmtCoffee());
		 }
		 //Coffee setter tested ======================================================================================
		 
		 //Milk setter
		 //invalid case first
		 @ParameterizedTest(name = "Invalid milk input: \"{0}\"")
		 @ValueSource(strings = {"-3", "five", "2.5", "", "  ", "1e2", "null"})
		 @DisplayName("Invalid milk inputs")
		 void milkSetterInvalidTest(String amt) {
		        assertThrows(RecipeException.class, () -> r.setAmtMilk(amt));
		    }
		 //valid case now
		 @Test
		 @DisplayName("Valid milk input")
		 void milkSetterValidTest() throws RecipeException {
			 String amt = "10";
			 r.setAmtMilk(amt);
			 assertEquals(10, r.getAmtMilk());
		 }
		 //Milk setter tested ======================================================================================
		 
		 //Sugar setter
		 //invalid case first
		 @ParameterizedTest(name = "Invalid sugar input: \"{0}\"")
		 @ValueSource(strings = {"-3", "five", "2.5", "", "  ", "1e2", "null"})
		 @DisplayName("Invalid sugar inputs")
		 void sugarSetterInvalidTest(String amt) {
		        assertThrows(RecipeException.class, () -> r.setAmtSugar(amt));
		    }
		 //valid case now
		 @Test
		 @DisplayName("Valid sugar input")
		 void sugarSetterValidTest() throws RecipeException {
			 String amt = "10";
			 r.setAmtSugar(amt);
			 assertEquals(10, r.getAmtSugar());
		 }
		 //Sugar setter tested ======================================================================================
		 
		 //Price setter
		 //invalid case first
		 @ParameterizedTest(name = "Invalid price input: \"{0}\"")
		 @ValueSource(strings = {"-3", "five", "2.5", "", "  ", "1e2", "null"})
		 @DisplayName("Invalid price inputs")
		 void priceSetterInvalidTest(String amt) {
		        assertThrows(RecipeException.class, () -> r.setPrice(amt));
		    }
		 //valid case now
		 @Test
		 @DisplayName("Valid price input")
		 void priceSetterValidTest() throws RecipeException {
			 String amt = "10";
			 r.setPrice(amt);
			 assertEquals(10, r.getPrice());
		 }
		 //Price setter tested ======================================================================================
		 
		 //Name setter
		 //invalid case first
		 @Test
		 @DisplayName("Null value for name")
		 void nameSetterInvalidTest() {
			 	r.setName(null);
		        assertEquals("", r.getName());
		    }
		 //valid case now
		 @Test
		 @DisplayName("Valid name input")
		 void nameSetterValidTest() {
			 String name = "ANYTHING";
			 r.setName(name);
			 assertEquals(name, r.getName());
		 }
		 //name setter tested ======================================================================================
		 
		 //testing to String method before and after setting
		 @Test
		 @DisplayName("To string method test")
		 void toStringMethodTest() throws RecipeException { //recipe exception cause the setter might cause it
			 assertEquals("", r.toString());
			 r.setName("recipe");
			 assertEquals("recipe", r.toString());		 
		 }
		 //To string method tested =========================================================================================
		 
		 //hash code under testing
		 //null situation
		 @Test
		 @DisplayName("HashCode null situation")
		 void hashCodeWithNullName() {
		     r.setName(null);
		     int hash = r.hashCode();
		     assertEquals(31, hash);
		 }
		 //Anything other than null situation
		 @Test
		 @DisplayName("HashCode non-null situation")
		 @Timeout(value = 300, unit = TimeUnit.MILLISECONDS)
		 void hashCodeWithName() throws InterruptedException { //cause we added sleep to stimulate huge time hashing larger names
		     r.setName("Recipe");
		     int hash = r.hashCode();
		     Thread.sleep(100);
		     assertEquals(31 + "Recipe".hashCode(), hash);
		 }
		 //hash code method covered ======================================================================================
		 
		//testing equals method
		//Reference to same object case
		 @Test
		 @DisplayName("Same object eqality")
		 void testSameObjectEquality() {
		     assertTrue(r.equals(r));
		    }
		 //Object is null
		 @Test
		 @DisplayName("Null to recipe eqality")
		 void testNullEquality() {
		     assertFalse(r.equals(null));
		    }
		 //Different class
		 @Test
		 @DisplayName("Recipe to different class eqality")
		 void testDifferentClassEquality() {
		     assertFalse(r.equals(1));
		    }
		 //Not same name
		@Test
		@DisplayName("Two different recipes neither is null eqality")
		void testDifferentRecipesNoNullsEquality() {
			String name = "Recipe";
			Recipe r1 = new Recipe();
		     r.setName(name+"1");
		     r1.setName(name+"2");
		     assertFalse(r.equals(r1));
		    }
		@Test
		@DisplayName("Two different recipes either is null eqality")
		void testDifferentRecipesWithNullsEquality() {
			String name = "Recipe";
			Recipe r1 = new Recipe();
		     r1.setName(name);
		     r.setName(null);
		     assertFalse(r.equals(r1));
		    }
		 //Same recipe
		 @ParameterizedTest
		 @ValueSource(strings = { "Recipe", "ANYTHING", "", "  ,df" }) 
		 @DisplayName("Two same recipes eqality")
		 void testSameRecipesEquality(String name) {
			 r.setName(name);
			 Recipe r1 = new Recipe();
			 r1.setName(name);
		     assertTrue(r.equals(r1));
		    }
		 //Both null in name scenario
		 @Test
		 @DisplayName("Both null names")
		 void testBothNullNamesEquality() {
		     r.setName(null);
		     Recipe r1 = new Recipe();
		     r1.setName(null);
		     assertTrue(r.equals(r1));
		 }
}
		 

//valid and invalid , 				descriptive names,       timeout,         parameterized,      diffrent assertions
//96%+ coverage

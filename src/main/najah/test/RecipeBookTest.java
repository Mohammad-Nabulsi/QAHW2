package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import main.najah.code.RecipeException;
@Execution(value = ExecutionMode.CONCURRENT)
class RecipeBookTest {
	
	
	//Starting with the constructor===========================================================================
	//No invalid input since no input was required so  
	//we only need to check existence of the object and array size
	
	@Test
	@DisplayName("Testing object existence and size upon construction")
	void constructorTest() {
		RecipeBook r = new RecipeBook();
		assertNotNull(r, "Should be intialized");
		assertEquals(4, r.getRecipes().length, "Size should be 4 since it was a final variable");
		}
	
	//Constructor tested=====================================================================================
	
	//Now test get recipes method=============================================================================
	@Test
	@DisplayName("Testing the recipe array retrieve method")
	void getRecipesTest() throws RecipeException {//Since set methods might cause a recipe exception
		//Since recipeArray is private and can't be accessed
		//We can't asserSame(rb.recipeArray, rb.getRecipes())
		//Hence we need to compare each recipe in there
		//And if they all match then method is working
		
		 RecipeBook rb = new RecipeBook();
		 String[] names = {"recipe1", "recipe2", "recipe3", "recipe4"};
		 String[] prices = {"10","20","30","40"};
		 String[] coffeeAmts = {"4", "3", "2", "1"};
		 String[] milkAmts = {"10", "11", "12", "13"};
		 String[] chocolateAmts = {"14", "13", "12", "11"};
		 String[] sugarAmts = {"0", "13", "2", "20"};
		 for (int i = 0; i < names.length; i++) {
	            Recipe r = new Recipe();
	            r.setName(names[i]);
	            r.setPrice(prices[i]);
	            r.setAmtCoffee(coffeeAmts[i]);
	            r.setAmtMilk(milkAmts[i]);
	            r.setAmtSugar(sugarAmts[i]);
	            r.setAmtChocolate(chocolateAmts[i]);
	            rb.addRecipe(r);
	        }
		 Recipe[] recipes = rb.getRecipes();
	        for (int i = 0; i < recipes.length; i++) {
	            Recipe r = recipes[i];
	            assertEquals(names[i], r.getName()); 
	            assertEquals(Integer.parseInt(prices[i]), r.getPrice()); 
	            assertEquals(Integer.parseInt(coffeeAmts[i]), r.getAmtCoffee()); 
	            assertEquals(Integer.parseInt(milkAmts[i]), r.getAmtMilk());
	            assertEquals(Integer.parseInt(sugarAmts[i]), r.getAmtSugar()); 
	            assertEquals(Integer.parseInt(chocolateAmts[i]), r.getAmtChocolate()); 
	        }
		 
	}
	
	//get recipes method tested ============================================================================
	
	//Testing add recipe method
	//Invalid inputs were ignored in class implementation
	//Assuming no recipe is invalid recipe
	//then we only need to check for valid inputs
	
	//First test behavior if recipe already exist
	@Test
	@DisplayName("Adding recipe when it already exist in the recipe book")
	void doesRecipeAlreadyExistTest() {
		Recipe r1 = new Recipe();
		Recipe r2 = new Recipe();
		RecipeBook rb = new RecipeBook();
		r1.setName("recipe1");
		r2.setName("recipe1"); 
		//Since equals function operates on name only(beside class, same reference, and null check)
		//we only need to give them same name
		rb.addRecipe(r1);
		assertFalse(rb.addRecipe(r2));
	}
	
	//Now let's test addition when recipe book is full
	@Test
	@DisplayName("Adding recipe when recipe book is full")
	void isRecipeBookFullTest() {
		Recipe r1 = new Recipe();
		Recipe r2 = new Recipe();
		Recipe r3 = new Recipe();
		Recipe r4 = new Recipe();
		Recipe r5 = new Recipe();
		r1.setName("recipe1");
		r2.setName("recipe2");
		r3.setName("recipe3");
		r4.setName("recipe4");
		r5.setName("recipe5");
		RecipeBook rb = new RecipeBook();
		rb.addRecipe(r1);
		rb.addRecipe(r2);
		rb.addRecipe(r3);
		rb.addRecipe(r4);
		assertFalse(rb.addRecipe(r5));
	}
	
	//Now let's test correctness of base case when the recipe doesn't 
	//already exist and recipe book isn't full
	@ParameterizedTest(name = "Should add recipe with name: {0}")		//Parameterized test
	@ValueSource(strings = {"Recipe1", "Recip2", "Recipe3"})
	@DisplayName("Adding recipe that doesn't already exist and the book isn't full")
	void baseAddingTest(String name) {
		Recipe r = new Recipe();
		r.setName(name);
		RecipeBook rb = new RecipeBook();
		boolean added = rb.addRecipe(r);
		assertEquals(r, rb.getRecipes()[0]);//is added recipe the same
		assertTrue(added);//does it return true as expected
	}
	
	//Add recipe method tested ========================================================================================
	
	//Test delete recipe
	
	//first if recipe at location X does not exist
	@Test
	@DisplayName("Deleting recipe at location X where it doesn't exist")
	void deleteNonExistentRecipe() {
		Recipe r = new Recipe();
		r.setName("recipe");
		RecipeBook rb = new RecipeBook();
		rb.addRecipe(r);
		//let's try deletion at following index since it doesn't exist (should return null)
		assertNull(rb.deleteRecipe(1));
	}
	//Check if recipe at location X has now new constructed recipe in place
	//(meaning name is "" as per default construction of a recipe
	@Test
	@DisplayName("Does the recipe at location X has a new empty recipe in place")
	void deletedLocationValueTest() {
		Recipe r = new Recipe();
		r.setName("recipe");
		RecipeBook rb = new RecipeBook();
		rb.addRecipe(r);
		rb.deleteRecipe(0);
		String name = rb.getRecipes()[0].getName();
		assertEquals("", name);
	}
	
	//now let's check if it returns the name of the deleted recipe
	@Test
	@DisplayName("Does deleting the recipe reurtn the deleted recipe's name")
	void deletedRecipeNameTest() {
		Recipe r = new Recipe();
		r.setName("recipe");
		RecipeBook rb = new RecipeBook();
		rb.addRecipe(r);
		String name = rb.getRecipes()[0].getName();
		assertEquals(name, rb.deleteRecipe(0));
	}
	
	//Delete recipe method tested =============================================================================
	
	//Test edit recipe 
	//Invalid inputs were ignored in class implementation
	//Assuming no recipe is invalid recipe
	//then we only need to check for valid inputs
	//first if recipe at location X does not exist
		@Test
		@DisplayName("Editing recipe at location X where it doesn't exist")
		void editNonExistentRecipe() {
			Recipe r = new Recipe();
			r.setName("recipe");
			Recipe r1 = new Recipe();
			r1.setName("recipe1");
			RecipeBook rb = new RecipeBook();
			rb.addRecipe(r);
			//let's try editing at following index since it doesn't exist (should return null)
			assertNull(rb.editRecipe(1,r1));
		}
		//Check if recipe at location X has now new replacement recipe in place
		//(meaning name is name of the new recipe 
		@Test
		@DisplayName("Does the recipe at location X has the name of the new recipe in place")
		void editedLocationValueTest() {
			Recipe r = new Recipe();
			r.setName("recipe");
			Recipe r1 = new Recipe();
			r1.setName("recipe1");
			RecipeBook rb = new RecipeBook();
			rb.addRecipe(r);
			rb.editRecipe(0, r1);
			String name = rb.getRecipes()[0].getName();
			assertEquals(r1.getName(), name);
			//Both names would be empty due to assigning "" to the name of the new recipe for no
			//reason instead of just swapping the values but both would be tested the same way
		}
		
		//now let's check if it returns the name of the edited recipe
		@Test
		@DisplayName("Does editing the recipe reurtn the edited recipe's name")
		@Timeout(value = 400, unit = TimeUnit.MILLISECONDS) //Timeout test
		void editedRecipeNameTest() throws InterruptedException { //cause we added sleep to stimulate server delay
			Recipe r = new Recipe();
			r.setName("recipe");
			Recipe r1 = new Recipe();
			r1.setName("recipe1");
			RecipeBook rb = new RecipeBook();
			rb.addRecipe(r);
			Thread.sleep(300);//Server delay
			String name = rb.getRecipes()[0].getName();
			assertEquals(name, rb.editRecipe(0, r1));
		}
		//Edit recipe method tested =============================================================================
		
		//Test to ensure parallel execution
		@Test
		@DisplayName("parallel1")
		void parallelOne() throws InterruptedException {
			Thread.sleep(1000);
		}
		//Another one
		@Test
		@DisplayName("parallel2")
		void parallelTwo() throws InterruptedException {
			Thread.sleep(1000);
		}
		//Another one
		@Test
		@DisplayName("parallel2")
		void parallelThree() throws InterruptedException {
			Thread.sleep(1000);
		}
		//Another one
		@Test
		@DisplayName("parallel2")
		void parallelFour() throws InterruptedException {
			Thread.sleep(1000);
		}

}

//In summary we checked for valid/invalid input, 	had descriptive display names, 		had multiple assertions, 		
//Had a timeout test, 		 a parameterized test fulfilling 3rd
// 100% coverage for class fulfilling 7th

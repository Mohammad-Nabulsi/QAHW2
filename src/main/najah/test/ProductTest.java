package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Product;


public class ProductTest {
    
	//Starting with constructor valid and invalid inputs............
	
	@Test //Assuming it's allowed for product to have the value of 0 at price since that was the code implementation
	@DisplayName("Testing creating products with negative price")
	void testConstructorWithNegativePrice() {
		int testPrice = -5;
		assertThrows(IllegalArgumentException.class, ()->{		//First unique assertion
			new Product("coffee", testPrice);
			});	
	}
	@DisplayName("Testing creating products")
	@ParameterizedTest(name = "Creating product: \"{0}\" with price {1}")				//One Parameterized test
	@CsvSource({
	    "Coffee, 10",	  //No need to test negative price here since it was tested previously
	    "Tea, 0",		  //Zero price should be okay since we specified failure only upon negative prices 
	    "'', 5",          // Empty name should also be okay
	    "Water, 5"       
	})
	void testConstructingNewProducts(String name, int price) {
	    Product product = new Product(name, price);
	    assertEquals(name, product.getName());				//Second unique assertion
	    assertEquals(price, product.getPrice());    
	}
	
	//Constructor tested..................
	
	//Testing applying discount with valid and invalid inputs...............
	
	@DisplayName("Testing applying discount for invalid inputs")
	@ParameterizedTest(name = "Applying {0}% discount for product")				//One Parameterized test
	@CsvSource({
	  "-1.5",	//Lesser value than zero
	   "-6.5755",		
	   "55.3333",//Greater value than 50
	   "172.450"    
	})
	void testInvalidDiscount(double discountPercentage) {
	    Product product = new Product("Coffee", 100);
	    assertThrows(IllegalArgumentException.class, ()->{
		    product.applyDiscount(discountPercentage);
	    }) ;
	}
	
	@DisplayName("Testing applying discount for valid inputs")
	@ParameterizedTest(name = "Applying {0}% discount for product")				//One Parameterized test
	@CsvSource({
	  "1.5",	//Greater value than zero
	   "0",			//edge case since in implementation a 0% discount is the minimum value accepted
	   "45.3333",//Lesser value than 50
	   "50"    	//edge case since in implementation a 50% discount is the maximum value accepted
	})
	void testvalidDiscount(double discountPercentage) {
	    Product product = new Product("Coffee", 100);
	    product.applyDiscount(discountPercentage);
	    assertEquals(discountPercentage, product.getDiscount()); 
	}
	
	//apply discount tested...............
	
	//Testing get final price method(No input here only test correctness)..................
	
	@DisplayName("Testing getting the final price for a product")
	@Test
	void testGetFinalPriceMethod() {
		double  price = 100;
		double discountPercentage = 2.5;
	    Product product = new Product("Coffee", price);
	    product.applyDiscount(discountPercentage);
	    assertEquals((price * (1 - discountPercentage / 100)), product.getFinalPrice()); 
	}
	
	//Get final price method tested..........
	
	//Testing get name method(No input here only test correctness) .................
	
	@Test
	@DisplayName("Testing get name method")
	void testGetName() {
		String testString = "Phone";
		Product p = new Product(testString, 10);
		assertEquals(testString, p.getName());
	}
	
	//Get name method tested.............
	
	//Testing get price method (No input here only test correctness) ..........
	
	@Test
	@DisplayName("Testing get price method")
	void testGetPrice() {
		double testPrice = 10;
		Product p = new Product("Phone", 10);
		assertEquals( testPrice, p.getPrice());
	}
	
	//Get price method tested.............
	
	//Testing get discount method (No input here only test correctness) ..........
	
	@Test
	@DisplayName("Testing get discount method")
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS) //A timeout test
	void testGetDiscount() throws InterruptedException{	//sleep method might cause an interrupted exception
		Product p = new Product("Coffee", 10);
		assertEquals(0, p.getDiscount()); //Since new products are initialized with 0 discount
		double testDiscountPercentege = 2.5;
		Thread.sleep(150);		//in order to add a timeout test and to stimulate fetching the info from a server
		//It should not cause an error the rest of the operation should not take more time
		p.applyDiscount(testDiscountPercentege);
		assertEquals(testDiscountPercentege, p.getDiscount());
	}
	
	//Get discount method tested..............
	
}


//in summary tested valid and invalid input, 	used descriptive names ,
//added a time out test, 			added a parameterized test
//and used multiple assertions fulfilling the 3rd requirement
//100% coverage for class fulfilling 7th requirement

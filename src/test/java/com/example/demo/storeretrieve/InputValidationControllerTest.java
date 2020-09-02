package com.example.demo.storeretrieve;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
* <h1>Input Validation Test</h1>
* The input validation tests checks if the input parameters for the API are 
* correct. There are two tests one to test the sunny day scenario and the 
* second to test the rainy day scenario
* <p>
* According to requirements only digits are allowed seperated by commas. 
* The sunnyDayTest() unit tests the code with correct string which has all digits
* The rainyDayTest() unit tests the code with incorrect string which doesnt have digits 
* and expects to get an exception
* </p>
* 
*
* @author  Lakshmanan Perichiappan
* @version 1.0
* @since   09.02.2020 
*/

@SpringBootTest
public class InputValidationControllerTest {
	
	String sunnyTestString="1,2,3,4";
	String rainyTestString="ABCD";
	
	/**
	   * This method is used to test the sunny day scenario with digits
	   * @param sunnyTestString
	*/
	@Test
	public void sunnyDayTest() throws InputValidationException{
		InputValidationController controller = new InputValidationController();
		assertEquals(true, controller.checkStoreInputParams(sunnyTestString));
	}
	
	/**
	   * This method is used to test the rainy day scenario with letters as input string
	   * which returns "InputValidationException"
	   * @param rainyTestString
	   * @exception InputValidationException
	*/
	
	@Test(expected=InputValidationException.class)
	public void rainyDayTest() throws InputValidationException  {
		InputValidationController controller = new InputValidationController();
		assertEquals(true, controller.checkStoreInputParams(rainyTestString));
		
	}

}

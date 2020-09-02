package com.example.demo.storeretrieve;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InputValidationControllerTest {
	
	String sunnyTestString="1,2,3,4";
	String rainyTestString="ABCD";
	
	@Test
	public void test() throws InputValidationException{
		InputValidationController controller = new InputValidationController();
		assertEquals(true, controller.checkStoreInputParams(sunnyTestString));
	}
	
	@Test(expected=InputValidationException.class)
	public void testException() throws InputValidationException  {
		InputValidationController controller = new InputValidationController();
		assertEquals(true, controller.checkStoreInputParams(rainyTestString));
		
	}

}

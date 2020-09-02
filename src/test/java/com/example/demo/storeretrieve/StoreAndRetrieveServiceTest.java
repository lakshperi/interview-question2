package com.example.demo.storeretrieve;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

/**
* <h1>Services Test</h1>
Tests the services layer with the spring profile of "InMemory". 
*
* @author  Lakshmanan Perichiappan
* @version 1.0
* @since   09.02.2020 
*/


@SpringBootTest
@ActiveProfiles({ "InMemory" })
@TestPropertySource(properties = {
	    "spring.profiles.active=InMemory",
	})
public class StoreAndRetrieveServiceTest {
	
	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	String testString1="1,2,3,4";
	String testString2="6,7,8,9";
	StoreAndRetrieveService service=new StoreAndRetrieveService("InMemory");
	
	@Autowired
    private Environment env;
	
	/**
	 * This test is used to check if the spring profile is InMemory
	*/ 
	@Test
    public void readProps() {
        String value = env.getProperty("spring.profiles.active") ;
        assertEquals("InMemory", value);
    }
	
	/**
	   * This test is used to check if the stored numbers are retrieved correctly.
	   * First the storeNumbersService() is called followed by retrieveAndShuffleNumberService() methods which 
	   * tests in the unit level if the store and retrieve is proper. 
	   * @param retrieveAndShuffleNumbersService
	   * @apiNote storeNumbersService
	   * 
	   * @apiNote retrieveAndShuffleNumbersService
	   * 
	*/
   @Test 
   void retrieveAndShuffleNumbersService() throws IdNotFoundException {
	   
	   Integer counter=service.storeNumbersService(testString1);
		assertEquals(0,counter);
  
		String retrievedString = service.retrieveAndShuffleNumbersService(0);
		String[] responseArray = retrievedString.split(",");

		String[] testStringArray = testString1.split(",");
		List<String> stringNumberList = Arrays.asList(testStringArray);

		for (String s : responseArray) {
			if (stringNumberList.contains(s)) {
				continue;
			} else {
				fail("Returned Shuffled string not same as Test String");
			}
		}
  
  }
   
   /**
	   * This test is used to check if the id is returned correctly for a storeNumbersService().
	   * 
	   * @apiNote storeNumbersService
	   * @param storeNumbersServiceTest
	*/
   
   @Test
	void storeNumbersServiceTest() {
		
		Integer counter=service.storeNumbersService(testString2);
		assertEquals(1,counter);
	}
	 

}

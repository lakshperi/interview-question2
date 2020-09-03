package com.example.demo.storeretrieve;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.DemoApplication;
import com.example.demo.persistence.StoreRepository;

/**
* <h1>Services Test</h1>
* Integration Tests the services layer with the spring profile of "InMemory" and Conditional bean of  
* "persistence.store=database"
* @author  Lakshmanan Perichiappan
* @version 1.0
* @since   09.02.2020 
*/

@SpringBootTest(classes=DemoApplication.class)
@TestPropertySource(properties = {
	    "persistence.store=database", "spring.profiles.active=h2",
	})
public class StoreAndRetrieveServiceTestForH2 {
	
	String testString1="1,2,3,4";
	String testString2="6,7,8,9";
	
	/**
	 * This test calls the interface StoreAndRetrieve which is instantiated by the StoreAndRetrieveDb class
	 * 
	 */
	@Autowired
	StoreAndRetrieve service=new StoreAndRetrieveDb();
	

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
	public void retrieveAndShuffleNumbersService() throws IdNotFoundException {

		Integer counter = service.storeNumbersArray(testString1);

		String retrievedString = service.retrieveAndShuffleNumbers(counter);
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
   
	@Test
	public void retrieveAndShuffleNumbersServiceSecond() throws IdNotFoundException {

		Integer counter = service.storeNumbersArray(testString2);

		String retrievedString = service.retrieveAndShuffleNumbers(counter);
		String[] responseArray = retrievedString.split(",");

		String[] testStringArray = testString2.split(",");
		List<String> stringNumberList = Arrays.asList(testStringArray);

		for (String s : responseArray) {
			if (stringNumberList.contains(s)) {
				continue;
			} else {
				fail("Returned Shuffled string not same as Test String");
			}
		}
		
	}
}

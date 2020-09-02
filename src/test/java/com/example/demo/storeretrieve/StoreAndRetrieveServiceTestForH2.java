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
Tests the services layer with the spring profile of "InMemory". 
*
* @author  Lakshmanan Perichiappan
* @version 1.0
* @since   09.02.2020 
*/

@SpringBootTest(classes=DemoApplication.class)
@ActiveProfiles({ "h2" })
@TestPropertySource(properties = {
	    "spring.profiles.active=h2",
	})
public class StoreAndRetrieveServiceTestForH2 {
	
	@Value("${spring.profiles.active}")
	private String activeProfile;

	String testString1="1,2,3,4";
	String testString2="6,7,8,9";
	
	@Autowired
	StoreAndRetrieveService service=new StoreAndRetrieveService("h2");
	
	@Autowired
    private Environment env;
	
	/**
	 * This test is used to check if the spring profile is InMemory
	*/ 
	@Test
    public void readProps() {
        String value = env.getProperty("spring.profiles.active") ;
        assertEquals("h2", value);
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

		Integer counter = service.storeNumbersService(testString1);

		String retrievedString = service.retrieveAndShuffleNumbersService(counter);
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
	void retrieveAndShuffleNumbersServiceSecond() throws IdNotFoundException {

		Integer counter = service.storeNumbersService(testString2);

		String retrievedString = service.retrieveAndShuffleNumbersService(counter);
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

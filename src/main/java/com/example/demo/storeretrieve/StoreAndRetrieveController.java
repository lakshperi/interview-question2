package com.example.demo.storeretrieve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * @author Laks
 * Exposing two HTTP end-points "/store" and "/permutation"
 * "/store" API will be used to store the input array of digits either in-memory or a database based on the condition bean 
 * instantiated from application.properites. 
 * 
 *  "/permutation" API will be used to retrieve the array of digits either from in-memory or a database and
 *  will shuffle/reorder the digits.
 */

@RestController
public class StoreAndRetrieveController {
	
	@Autowired
	private InputValidation validator;
	
	@Autowired
	private  StoreAndRetrieve storeAndRetrieveService; 
	
	
	@GetMapping("/store")
    public ResponseEntity<Integer> store(@RequestParam(name = "numbers") String inputArrayOfNumbers) throws InputValidationException {
		
		Integer id= null;
		
		boolean isInputValidationSuccess = validator.checkInputParams(inputArrayOfNumbers);
		
		if(isInputValidationSuccess) {
			   id = storeAndRetrieveService.storeNumbersArray(inputArrayOfNumbers);
		 }
		
		return new ResponseEntity<Integer>(id, new HttpHeaders(), HttpStatus.OK);	       	
    }
	
	@GetMapping("/permutation")
	 public ResponseEntity<String> permute(@RequestParam(name = "id") Integer id) throws IdNotFoundException {
		
		String retrievedValue= storeAndRetrieveService.retrieveAndShuffleNumbers(id);
		
		return new ResponseEntity<String>(retrievedValue, new HttpHeaders(), HttpStatus.OK);
	}

	
}

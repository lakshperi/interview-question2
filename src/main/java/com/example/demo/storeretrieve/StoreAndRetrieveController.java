package com.example.demo.storeretrieve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StoreAndRetrieveController {
	
	@Autowired
	InputValidationController validation;
	
	@Autowired
	StoreAndRetrieveService service;
	
	
	@GetMapping("/store")
    public ResponseEntity<Integer> store(@RequestParam(name = "numbers") String inputArrayOfNumbers) throws InputValidationException {
		
		Integer i= null;
		
		boolean isInputValidationSuccess=validation.checkStoreInputParams(inputArrayOfNumbers);
		if(isInputValidationSuccess==true) {
			   i=service.storeNumbersService(inputArrayOfNumbers);
		 }
		
		return new ResponseEntity<Integer>(i, new HttpHeaders(), HttpStatus.OK);	       	
    }
	
	@GetMapping("/permutation")
	 public ResponseEntity<String> permute(@RequestParam(name = "id") Integer id) throws IdNotFoundException {
		String retrievedValue= service.retrieveAndShuffleNumbersService(id);
		
		return new ResponseEntity<String>(retrievedValue, new HttpHeaders(), HttpStatus.OK);
	}

	
}

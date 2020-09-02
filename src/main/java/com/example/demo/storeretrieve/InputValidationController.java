package com.example.demo.storeretrieve;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class InputValidationController {
	
	

	public boolean checkStoreInputParams(String inputArrayOfNumbers) throws InputValidationException {
		String[] stringArrayOfNumbers= inputArrayOfNumbers.split(",");
		//Input Validation
	    for (String number : stringArrayOfNumbers) {
	    	try {
	            Integer.parseInt(number);
	        } catch (NumberFormatException e) {
	        	throw new InputValidationException("Input Validation Failed- Only numbers are allowed");
	        }
	    }
	    return true;
	}

	
}

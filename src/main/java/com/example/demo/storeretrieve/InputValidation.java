package com.example.demo.storeretrieve;


import org.springframework.stereotype.Component;

/**
 * 
 * @author Laks
 * This class validates the input array give by the user and gives a boolean true if the array has digits. 
 * If the array doesnt have digits fully, an InputValidationException is thrown. 
 *
 */
@Component
public class InputValidation {

	public boolean checkInputParams(String inputArrayOfNumbers) throws InputValidationException {
		String[] stringArrayOfNumbers = inputArrayOfNumbers.split(",");
		// Input Validation
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

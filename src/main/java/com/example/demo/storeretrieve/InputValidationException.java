package com.example.demo.storeretrieve;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InputValidationException extends Exception {
	

	public InputValidationException(String msg) {
		 super(msg);
	}
}


package com.example.demo.storeretrieve;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Laks
 * This exception class is used to give the HTTP Response of 403 when a user queries an Id which is not present in 
 * the hashmap(Part 1) or Database(Part 2)
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdNotFoundException extends Exception {
	public IdNotFoundException(String msg) {
		 super(msg);
	}
}

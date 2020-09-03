package com.example.demo.storeretrieve;

/**
 * 
 * @author Laks
 * According to the requirements, the Part 1 should store the input array of digits in-memory(like a hashmap) and in 
 * Part 2, the input array of digits needs to be persisted in a Database(InMemory database or relational/NoSQL databases).
 * Taking a design choice of using an interface for the service layer which will be instantiated based on the inputs in 
 * application.yml file. 
 */

public interface StoreAndRetrieve {
	
	public int storeNumbersArray(String inputArrayOfNumbers);
	
	public String retrieveAndShuffleNumbers(Integer id) throws IdNotFoundException;

}

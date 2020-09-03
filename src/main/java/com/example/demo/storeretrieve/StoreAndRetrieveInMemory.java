package com.example.demo.storeretrieve;

/**
 * @author Laks
 * This is a Conditional bean which gets instantiated based on "peristence.store" property having a value of "InMemory"
 * in application.yml. 
 * This bean implements the interface "StoreAndRetrieve"
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty (name = "persistence.store", havingValue = "InMemory")
public class StoreAndRetrieveInMemory implements StoreAndRetrieve {

	static AtomicInteger atomicIntegerCount=new AtomicInteger();
	
	//ConcurrentHashMap is considered for Threading consideration. 
	Map<Integer,String> counterNumbersMap= new ConcurrentHashMap<Integer,String>();
	
	@Override
	public int storeNumbersArray(String inputArrayOfNumbers) {
		Integer i=null;
		i=Integer.valueOf(atomicIntegerCount.getAndIncrement());
		counterNumbersMap.put(i,inputArrayOfNumbers);
		return i;
	}

	@Override
	public String retrieveAndShuffleNumbers(Integer id) throws IdNotFoundException {
	
		String valueRead= counterNumbersMap.get(id);
		
		if(valueRead==null) {
			throw new IdNotFoundException("Id not found Exception");
		}
		
		String[] stringArrayOfNumbers= valueRead.split(",");
		

	    List<String> stringNumberList= Arrays.asList(stringArrayOfNumbers);
	    		 		
		List<Integer> integerNumberList = stringNumberList.stream()
				               .map(s -> Integer.parseInt(s))
				               	.collect(Collectors.toList());
		
		List<Integer> copiedList= new ArrayList<Integer>(integerNumberList);
		
		Collections.shuffle(copiedList);
		
		return copiedList.stream().map(String::valueOf)
		        .collect(Collectors.joining(","));
	}
	
	

}

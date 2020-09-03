package com.example.demo.storeretrieve;

/**
 * @author Laks
 * This is a Conditional bean which gets instantiated based on "peristence.store" property having a value of "database"
 * in application.yml. 
 * This bean implements the interface "StoreAndRetrieve"
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.example.demo.persistence.StoreEntity;
import com.example.demo.persistence.StoreRepository;

@Component
@ConditionalOnProperty (name = "persistence.store", havingValue = "database")
public class StoreAndRetrieveDb implements StoreAndRetrieve {

	@Autowired
	StoreRepository repository;
	
	//For Threading consideration, the counter is considered to be an Atomic Integer. 
	static AtomicInteger atomicIntegerCount=new AtomicInteger();
	
	@Override
	public int storeNumbersArray(String inputArrayOfNumbers) {
		Integer i=null;
		i=Integer.valueOf(atomicIntegerCount.getAndIncrement());
		StoreEntity storeEntity= new StoreEntity();
		storeEntity.setKey(i);
		storeEntity.setValue(inputArrayOfNumbers);
		repository.save(storeEntity);
		return i;
	}

	@Override
	public String retrieveAndShuffleNumbers(Integer id) throws IdNotFoundException {
		
		String valueRead = null;
		Optional<StoreEntity> optional= repository.findById(id);
		if(optional.isPresent()) {
			valueRead=optional.get().getValue();
			System.out.println(valueRead);
		}
		else {
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

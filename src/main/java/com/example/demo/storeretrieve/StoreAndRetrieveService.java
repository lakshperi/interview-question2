package com.example.demo.storeretrieve;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;


import org.springframework.stereotype.Service;

import lombok.Data;

@Service
public class StoreAndRetrieveService {
	
	@Value("${spring.profiles.active}")
	private String activeProfile;
	
	public static final String IN_MEMORY_PROFILE="InMemory";
	
	static AtomicInteger atomicIntegerCount=new AtomicInteger();
	
	Map<Integer,String> counterNumbersMap= new ConcurrentHashMap<Integer,String>();
	
	public StoreAndRetrieveService() {}
	
	public StoreAndRetrieveService(@Value("${spring.profiles.active}") String activeProfile){
	       this.activeProfile = activeProfile;
	}
	
	int storeNumbersService(String inputArrayOfNumbers) {
		Integer i=null;
		
		i=Integer.valueOf(atomicIntegerCount.getAndIncrement());
		if(activeProfile.equals(IN_MEMORY_PROFILE)) {			
			
			counterNumbersMap.put(i,inputArrayOfNumbers);
		}
		
		return i;
	}
	
	String retrieveAndShuffleNumbersService(Integer id) throws IdNotFoundException{

		String valueRead = null;
		
		if(activeProfile.equals(IN_MEMORY_PROFILE)) {
			valueRead= counterNumbersMap.get(id);
		}
		
		
		if(valueRead == null) {
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

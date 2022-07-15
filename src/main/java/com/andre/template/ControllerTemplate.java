package com.andre.template;

import java.util.Map;

import com.andre.exceptions.ArraysAreNotTheSameSizeException;

public class ControllerTemplate {
	
	protected Map<String,Object> respMap;
	
	protected void putEntriesIntoTheResponseMap(String[] key, Object[] value) throws ArraysAreNotTheSameSizeException {
		if(key.length != value.length) 
			throw new ArraysAreNotTheSameSizeException();
			
		for(int i=0; i<key.length; i++) {
			respMap.put(key[i], value[i]);
		}
	}

}

package com.andre.template;

import java.util.Map;

import com.andre.exceptions.ArraysAreNotTheSameSizeException;

/**
 * ControllerTemplate provides a base class for controllers,
 * allowing them to manage a response map and handle common operations.
 * It includes a method to put entries into the response map,
 * ensuring that the keys and values arrays are of the same size.
 */
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

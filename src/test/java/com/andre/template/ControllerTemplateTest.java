package com.andre.template;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.andre.constants.Constants;
import com.andre.exceptions.ArraysAreNotTheSameSizeException;

class ControllerTemplateTest {

	@Test
	void testPutEntriesIntoTheResponseMap_throwArraysAreNotTheSameSizeException() throws ArraysAreNotTheSameSizeException {
		ControllerTemplate controllerTemplate = new ControllerTemplate(); 
		
		String[] keys = {Constants.DATA,Constants.MSG};
		
		Object[] values = {Constants.SUCCESS}; 
		
		assertThrows(ArraysAreNotTheSameSizeException.class, () -> controllerTemplate.putEntriesIntoTheResponseMap(keys,values));
	}
	
}

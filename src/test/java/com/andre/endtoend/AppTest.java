package com.andre.endtoend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.andre.selenium.SeleniumUtil;


class AppTest extends SeleniumUtil {
	
	@Test
	void testUrl_successfullyGetPage(){
		if(driver != null)
			assertEquals("WriteClientReport",driver.getTitle());
		
	}
	
}

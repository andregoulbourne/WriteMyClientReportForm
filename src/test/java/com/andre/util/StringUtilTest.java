package com.andre.util;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StringUtilTest {
	
	@Test
	void testEquals() {
		String testString1 = "abas";
		String testString2 = "abasf";
		String testString3 = "";
		String testString4 = null;
		
		assertFalse(StringUtil.equals(testString1,testString2));
		assertFalse(StringUtil.equals(testString1,testString3));
		assertFalse(StringUtil.equals(testString1,testString4));
		assertFalse(StringUtil.equals(testString4,testString1));
		assertTrue(StringUtil.equals(testString1,testString1));
	}
	
	@Test
	void testEqualsIgnoreCase() {
		String testString1 = "abas";
		String testString2 = "abasf";
		String testString3 = "";
		String testString4 = null;
		
		assertFalse(StringUtil.equalsIgnoreCase(testString1,testString2));
		assertFalse(StringUtil.equalsIgnoreCase(testString1,testString3));
		assertFalse(StringUtil.equalsIgnoreCase(testString1,testString4));
		assertFalse(StringUtil.equalsIgnoreCase(testString4,testString1));
		assertTrue(StringUtil.equalsIgnoreCase(testString1,testString1));
	}
	
	@Test
	void testIsBlank() {
		String st1= "";
		String st2 = null;
		String st3 = "   ";
		String st4 = "faagjhpoia";
		
		assertTrue(StringUtil.isBlank(st1));
		assertTrue(StringUtil.isBlank(st2));
		assertTrue(StringUtil.isBlank(st3));
		assertFalse(StringUtil.isBlank(st4));
	}

}

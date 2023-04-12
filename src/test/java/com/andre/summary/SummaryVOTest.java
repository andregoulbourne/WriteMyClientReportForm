package com.andre.summary;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.andre.summary.SummaryVO;



class SummaryVOTest {

	private SummaryVO summaryObject;

	@BeforeEach
	void setup() {
		this.summaryObject = new SummaryVO();
	}

	@Test
	void testGetterAndSetters_setAnotherId() {
		String expectedResult = "1";
		summaryObject.setId("1");
		assertEquals(expectedResult, summaryObject.getId());
	}

	@Test
	void testGetterAndSetters_setAnotherStudent() {
		String expectedResult = "Andre";
		summaryObject.setStudent("Andre");
		assertEquals(expectedResult, summaryObject.getStudent());
	}
	
	@Test
	void testGetterAndSetters_setAnotherStatus() {
		String expectedResult = "1";
		summaryObject.setStatus("1");
		assertEquals(expectedResult, summaryObject.getStatus());
	}

	@Test
	void testGetterAndSetters_setAnotherMadeADifference() {
		boolean expectedResult = true;
		summaryObject.setMadeADifference(true);
		assertEquals(expectedResult, summaryObject.isMadeADifference());
	}


	@Test
	void testGetterAndSetters_setAnotherCoveredValue() {
		String expectedResult = "coveredValue";
		summaryObject.setCoveredValue("coveredValue");
		assertEquals(expectedResult, summaryObject.getCoveredValue());
	}

	@Test
	void testGetterAndSetters_setAnotherRecomendation() {
		String expectedResult = "1";
		summaryObject.setRecomendation("1");
		assertEquals(expectedResult, summaryObject.getRecomendation());
	}
	
	@Test
	void testGetterAndSetters_setGender() {
		String expectedResult = "1";
		summaryObject.setGender("1");
		assertEquals(expectedResult, summaryObject.getGender());
	}

}

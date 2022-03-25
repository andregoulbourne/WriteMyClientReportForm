package com.andre.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class SummaryObjectVOTest {

	private SummaryObjectVO summaryObject;

	@Before
	public void setup() {
		this.summaryObject = new SummaryObjectVO();
	}

	@Test
	public void testGetterAndSetters_setAnotherId() {
		String expectedResult = "1";
		summaryObject.setId("1");
		assertEquals(expectedResult, summaryObject.getId());
	}

	@Test
	public void testGetterAndSetters_setAnotherStudent() {
		String expectedResult = "Andre";
		summaryObject.setStudent("Andre");
		assertEquals(expectedResult, summaryObject.getStudent());
	}

	@Test
	public void testGetterAndSetters_setAnotherSubject() {
		String expectedResult = "Math 121";
		summaryObject.setSubject("Math 121");
		assertEquals(expectedResult, summaryObject.getSubject());
	}

	@Test
	public void testGetterAndSetters_setAnotherStatusStart() {
		String expectedResult = "Medium";
		summaryObject.setStatusStart("Medium");
		assertEquals(expectedResult, summaryObject.getStatusStart());
	}

	@Test
	public void testGetterAndSetters_setAnotherMadeADifference() {
		boolean expectedResult = true;
		summaryObject.setMadeADifference(true);
		assertEquals(expectedResult, summaryObject.isMadeADifference());
	}

	@Test
	public void testGetterAndSetters_setAnotherCoveredKey() {
		String expectedResult = "coveredKey";
		summaryObject.setCoveredKey("coveredKey");
		assertEquals(expectedResult, summaryObject.getCoveredKey());
	}

	@Test
	public void testGetterAndSetters_setAnotherCoveredValue() {
		String expectedResult = "coveredValue";
		summaryObject.setCoveredValue("coveredValue");
		assertEquals(expectedResult, summaryObject.getCoveredValue());
	}

	@Test
	public void testGetterAndSetters_setAnotherRecomendation() {
		int expectedResult = 1;
		summaryObject.setRecomendation(1);
		assertEquals(expectedResult, summaryObject.getRecomendation());
	}

	@Test
	public void testGetterAndSetters_setMapRecommendations() {
		Map<Integer, String> expectedResult = new HashMap<>();
		summaryObject.setMapRecommendations(expectedResult);
		assertEquals(expectedResult, summaryObject.getMapRecommendations());
	}

}

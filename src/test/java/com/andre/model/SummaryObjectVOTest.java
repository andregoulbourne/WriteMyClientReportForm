package com.andre.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

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
		var expectedResult = "1";
		summaryObject.setId("1");
		assertEquals(expectedResult, summaryObject.getId());
	}

	@Test
	public void testGetterAndSetters_setAnotherStudent() {
		var expectedResult = "Andre";
		summaryObject.setStudent("Andre");
		assertEquals(expectedResult, summaryObject.getStudent());
	}
	
	@Test
	public void testGetterAndSetters_setAnotherStatusStart() {
		var expectedResult = 1;
		summaryObject.setStatusStart(1);
		assertEquals(expectedResult, summaryObject.getStatusStart());
	}

	@Test
	public void testGetterAndSetters_setAnotherStatus() {
		var expectedResult = new HashMap<Integer,String>();
		summaryObject.setStatus(expectedResult);
		assertEquals(expectedResult, summaryObject.getStatus());
	}

	@Test
	public void testGetterAndSetters_setAnotherMadeADifference() {
		var expectedResult = true;
		summaryObject.setMadeADifference(true);
		assertEquals(expectedResult, summaryObject.isMadeADifference());
	}

	@Test
	public void testGetterAndSetters_setAnotherCoveredKey() {
		var expectedResult = "coveredKey";
		summaryObject.setCoveredKey("coveredKey");
		assertEquals(expectedResult, summaryObject.getCoveredKey());
	}

	@Test
	public void testGetterAndSetters_setAnotherCoveredValue() {
		var expectedResult = "coveredValue";
		summaryObject.setCoveredValue("coveredValue");
		assertEquals(expectedResult, summaryObject.getCoveredValue());
	}

	@Test
	public void testGetterAndSetters_setAnotherRecomendation() {
		var expectedResult = 1;
		summaryObject.setRecomendation(1);
		assertEquals(expectedResult, summaryObject.getRecomendation());
	}

	@Test
	public void testGetterAndSetters_setMapRecommendations() {
		var expectedResult = new HashMap<Integer,String>();
		summaryObject.setMapRecommendations(expectedResult);
		assertEquals(expectedResult, summaryObject.getMapRecommendations());
	}

}

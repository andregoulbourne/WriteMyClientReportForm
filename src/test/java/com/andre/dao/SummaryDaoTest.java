package com.andre.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.andre.constants.Constants;
import com.andre.model.SummaryVO;

class SummaryDaoTest {
	
	private SummaryDao reader;
	
	private SummaryVO object1;
	private SummaryVO object;
	
	@BeforeEach
	void setup() {
		reader = new SummaryDao();
	}
	
	@Test
	void testSummaryDaoFile_returnsListSummaryObjects() {
		
		assertDoesNotThrow(() -> reader.readCSVFile());
		
		reader.setFileInPath("./src/test/resources/Summarys.csv");
		
		List<SummaryVO> expected = new ArrayList<>();
		expected.add(object);
		expected.add(object1);
		
		
		System.out.println(expected);
		List<SummaryVO> actual = reader.readCSVFile();
		assertEquals(expected.size(), actual.size());
		assertEquals("definition of derivatives using limits and implicit differentiation", actual.get(0).getCoveredValue());
	}
	
	@Test
	void testReadCSVFileSingleEntry_returnsSummaryObjects() {
		
		assertDoesNotThrow(() -> reader.readCSVFileSingleEntry("1"));
		
		reader.setFileInPath("./src/test/resources/Summarys.csv");
		
		SummaryVO object = new SummaryVO();
		object.setId("1");
		object.setStudent("Mike");
		object.setStatus("1");
		object.setMadeADifference(false);
		object.setCoveredValue("definition of derivatives using limits and implicit differentiation");
		object.setRecomendation("work more examples of the problem to keep the problem fresh in memory");
		object.setGender("M");
		
		
		SummaryVO actual = reader.readCSVFileSingleEntry("1");
		assertEquals(object.getId(), actual.getId());
		assertEquals(object.getStudent(), actual.getStudent());
	}
	
	@Test
	void testUpdateCSVFile_returnsZeroOrOne() {
		
		assertDoesNotThrow(() -> reader.csvFileEntry(null, Constants.UPDATE));
		assertEquals(0,reader.csvFileEntry(null, Constants.UPDATE));
		
		reader.setFileInPath("./src/test/resources/Summarys.csv");
		
		assertEquals(0,reader.csvFileEntry(null, Constants.UPDATE));
		
		SummaryVO o = new SummaryVO();
		o.setStudent("Mike");
		o.setId("1");
		
		assertEquals(1,reader.csvFileEntry(o, Constants.UPDATE));
		
		
	}
	
	@Test
	void testAddAndDeleteCSVFileEntry_returnsZeroOrOne() {
		
		assertDoesNotThrow(() -> reader.csvFileEntry(null, Constants.ADD));
		assertEquals(0,reader.csvFileEntry(null, Constants.ADD));
		
		reader.setFileInPath("./src/test/resources/Summarys.csv");
		
		assertEquals(0,reader.csvFileEntry(null, Constants.ADD));
		
		SummaryVO o = new SummaryVO();
		o.setId("3");
		o.setCoveredValue("agag");
		o.setGender("he");
		o.setMadeADifference(false);
		o.setRecomendation("afhakf");
		o.setStatus("2");
		o.setStudent("Cool Kid");
		
		//Checking whether it add one the
		assertEquals(1,reader.csvFileEntry(o, Constants.ADD));
		assertEquals(0,reader.csvFileEntry(o, Constants.ADD));
		
		assertDoesNotThrow(() ->reader.csvFileEntry(null, Constants.DELETE));
		assertEquals(1,reader.csvFileEntry(o,Constants.DELETE));
		assertEquals(0,reader.csvFileEntry(o,Constants.DELETE));
	}

	
	@Test
	void testValidationMsg() {
		String validationMsg = "Some validation message";
		reader.setValidationMsg(validationMsg);
		assertEquals(validationMsg,reader.getValidationMsg());
	}
}

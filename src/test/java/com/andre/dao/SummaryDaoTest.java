package com.andre.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.andre.model.SummaryVO;

public class SummaryDaoTest {
	
	private SummaryDao reader;
	
	@Test
	public void testSummaryDaoFile_returnsListSummaryObjects() {
		
		reader = new SummaryDao();
		
		assertDoesNotThrow(() -> reader.readCSVFile());
		
		reader.setFileInPath("./src/test/resources/Summarys.csv");
		
		List<SummaryVO> expected = new ArrayList<>();
		SummaryVO object = new SummaryVO();
		object.setId("1");
		object.setStudent("Mike");
		object.setStatus("1");
		object.setMadeADifference(false);
		object.setCoveredValue("definition of derivatives using limits and implicit differentiation");
		object.setRecomendation("work more examples of the problem to keep the problem fresh in memory");
		object.setGender("M");
		expected.add(object);
		
		SummaryVO object1 = new SummaryVO();
		object1.setId("2");
		object1.setStudent("Saddy");
		object1.setStatus("0");
		object1.setMadeADifference(true);
		object1.setCoveredValue("critical numbers");
		object1.setRecomendation("work more examples of the problem to keep the problem fresh in memory");
		object1.setGender("F");
		expected.add(object1);
		
		
		System.out.println(expected);
		List<SummaryVO> actual = reader.readCSVFile();
		assertEquals(expected.size(), actual.size());
		assertEquals("definition of derivatives using limits and implicit differentiation", actual.get(0).getCoveredValue());
	}
	
	@Test
	public void testReadCSVFileSingleEntry_returnsSummaryObjects() {
		
		reader = new SummaryDao();
		
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
	public void testUpdateCSVFile_returnsZeroOrOne() {
		
		reader = new SummaryDao();
		
		assertDoesNotThrow(() -> reader.updateCSVFile(null));
		assertEquals(0,reader.updateCSVFile(null));
		
		reader.setFileInPath("./src/test/resources/Summarys.csv");
		
		assertEquals(0,reader.updateCSVFile(null));
		
		SummaryVO o = new SummaryVO();
		o.setStudent("Mike");
		o.setId("1");
		
		assertEquals(1,reader.updateCSVFile(o));
		
		
	}
	
	@Test
	public void testAddAndDeleteCSVFileEntry_returnsZeroOrOne() {
		
		reader = new SummaryDao();
		
		assertDoesNotThrow(() -> reader.addCSVFileEntry(null));
		assertEquals(0,reader.addCSVFileEntry(null));
		
		reader.setFileInPath("./src/test/resources/Summarys.csv");
		
		assertEquals(0,reader.addCSVFileEntry(null));
		
		SummaryVO o = new SummaryVO();
		o.setId("3");
		o.setCoveredValue("agag");
		o.setGender("he");
		o.setMadeADifference(false);
		o.setRecomendation("afhakf");
		o.setStatus("2");
		o.setStudent("Cool Kid");
		
		assertEquals(1,reader.addCSVFileEntry(o));
		
		assertDoesNotThrow(() ->reader.deleteCSVFileEntry(null));
		assertEquals(1,reader.deleteCSVFileEntry(o));
		assertEquals(0,reader.deleteCSVFileEntry(o));
	}

}

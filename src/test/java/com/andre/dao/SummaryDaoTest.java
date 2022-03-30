package com.andre.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.andre.model.SummaryObjectVO;

public class SummaryDaoTest {
	
	private SummaryDao reader;
	
	@Test
	public void testSummaryDaoFile_returnsListSummaryObjects() {
		
		reader = new SummaryDao();
		
		assertDoesNotThrow(() -> reader.readCSVFile());
		
		reader.setFileInPath("./src/test/resources/Summarys.csv");
		
		var expected = new ArrayList<SummaryObjectVO>();
		var object = new SummaryObjectVO();
		object.setId("1");
		object.setStudent("Mike");
		object.setStatus("1");
		object.setMadeADifference(false);
		object.setCoveredValue("definition of derivatives using limits and implicit differentiation");
		object.setRecomendation("work more examples of the problem to keep the problem fresh in memory");
		object.setGender("M");
		expected.add(object);
		
		var object1 = new SummaryObjectVO();
		object1.setId("2");
		object1.setStudent("Saddy");
		object1.setStatus("0");
		object1.setMadeADifference(true);
		object1.setCoveredValue("critical numbers");
		object1.setRecomendation("work more examples of the problem to keep the problem fresh in memory");
		object1.setGender("F");
		expected.add(object1);
		
		
		System.out.println(expected);
		var actual = reader.readCSVFile();
		assertEquals(expected.size(), actual.size());
		assertEquals("definition of derivatives using limits and implicit differentiation", actual.get(0).getCoveredValue());
	}
	
	@Test
	public void testReadCSVFileSingleEntry_returnsSummaryObjects() {
		
		reader = new SummaryDao();
		
		assertDoesNotThrow(() -> reader.readCSVFileSingleEntry("1"));
		
		reader.setFileInPath("./src/test/resources/Summarys.csv");
		
		var object = new SummaryObjectVO();
		object.setId("1");
		object.setStudent("Mike");
		object.setStatus("1");
		object.setMadeADifference(false);
		object.setCoveredValue("definition of derivatives using limits and implicit differentiation");
		object.setRecomendation("work more examples of the problem to keep the problem fresh in memory");
		object.setGender("M");
		
		
		var actual = reader.readCSVFileSingleEntry("1");
		assertEquals(object.getId(), actual.getId());
		assertEquals(object.getStudent(), actual.getStudent());
	}
	
	@Test
	public void testUpdateCSVFile_returnsZeroOrOne() {
		
		reader = new SummaryDao();
		
		assertDoesNotThrow(() -> reader.updateCSVFile(null,null));
		assertEquals(0,reader.updateCSVFile(null,null));
		
		reader.setFileInPath("./src/test/resources/Summarys.csv");
		
		assertEquals(0,reader.updateCSVFile(null,null));
		
		var o = new SummaryObjectVO();
		o.setStudent("Mike");
		
		assertEquals(1,reader.updateCSVFile("1",o));
		
		
	}

}

package com.andre.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.andre.model.SummaryObjectVO;

public class ReadCSVTest {
	
	private ReadCsv reader;
	
	@Test
	public void testReadCSVFile_returnsListSummaryObjects() {
		
		reader = new ReadCsv();
		
		assertDoesNotThrow(() -> reader.readCSVFile());
		
		reader.setFileInPath("./src/test/resources/Summarys.csv");
		
		var expected = new ArrayList<SummaryObjectVO>();
		var object = new SummaryObjectVO();
		object.setStudent("Andre");
		object.setStatus("1");
		object.setMadeADifference(true);
		object.setCoveredValue("critical numbers");
		object.setRecomendation("work more examples of the problem to keep the problem fresh in memory");
		expected.add(object);
		
		var object1 = new SummaryObjectVO();
		object1.setStudent("Andre");
		object1.setStatus("0");
		object1.setMadeADifference(true);
		object1.setCoveredValue("critical numbers");
		object1.setRecomendation("work more examples of the problem to keep the problem fresh in memory");
		expected.add(object);
		
		var actual = reader.readCSVFile();
		assertEquals(expected.size(), actual.size());
		assertEquals("critical numbers, increasing decreasing test", actual.get(0).getCoveredValue());
	}

}

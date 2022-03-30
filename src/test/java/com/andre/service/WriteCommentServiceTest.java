package com.andre.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.andre.model.SummaryVO;

public class WriteCommentServiceTest {
	
	@Test
	public void testWriteComment_returnTutorCommnentString() {
		var service = new WriteCommentService();
		var expected = "On a scale from 1-3, Andre confidence level with the material was at a 3 at the beginning of the session.\r\n"
				+ "\r\n"
				+ "In the session we worked on finding critical numbers and increasing decreasing test.\r\n"
				+ "\r\n"
				+ "After the session Andre said he was feeling more confident with the material then prior.\r\n"
				+ "\r\n"
				+ "Recommendations: work on more examples of problems to gain more confidence."
				+ "\r\n"
				+ "\r\n";
		
		var givenList = new ArrayList<SummaryVO>();
		var object = new SummaryVO();
		object.setStudent("Andre");
		object.setStatus("3");
		object.setMadeADifference(true);
		object.setCoveredValue("critical numbers and increasing decreasing test");
		object.setRecomendation("work on more examples of problems to gain more confidence");
		object.setGender("he");
		givenList.add(object);
		
		assertEquals(expected, service.writeComment(givenList));
	}
}

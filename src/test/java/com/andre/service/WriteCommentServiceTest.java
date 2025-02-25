package com.andre.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.andre.model.SummaryVO;

class WriteCommentServiceTest {
	
	@Test
	void testWriteComment_returnTutorCommnentString() {
		WriteCommentService service = new WriteCommentService();
		String expected = """
On a scale from 1-3, Andre confidence level with the material was at a 3 at the beginning of the session.

In the session we worked on finding critical numbers and increasing decreasing test.

After the session Andre said he was feeling more confident with the material then prior.

Recommendations: work on more examples of problems to gain more confidence.

""";
		
		List<SummaryVO> givenList = new ArrayList<>();
		SummaryVO object = new SummaryVO();
		object.setStudent("Andre");
		object.setStatus("3");
		object.setMadeADifference(true);
		object.setCoveredValue("critical numbers and increasing decreasing test");
		object.setRecommendation("work on more examples of problems to gain more confidence");
		object.setGender("he");
		givenList.add(object);
		
		assertEquals(expected, service.writeComment(givenList));
		assertDoesNotThrow(() -> service.writeComment(null));
	}
}

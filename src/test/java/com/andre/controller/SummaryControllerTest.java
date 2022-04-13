package com.andre.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.andre.controllers.SummaryController;
import com.andre.model.SummaryVO;
import com.andre.service.SummaryService;
import com.andre.service.WriteCommentService;

@SpringBootTest
class SummaryControllerTest {
	
	@InjectMocks
	private SummaryController controller;
	
	@Mock
	private WriteCommentService writeCommentService;
	
	@Mock
	private SummaryService summaryService;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testGetAllSummarys() {
		ReflectionTestUtils.setField(controller, "summaryService", summaryService);
		assertDoesNotThrow(() -> controller.getAllSummarys());
	}
	
	@Test
	void testUpdateASummary() {
		SummaryVO summary=new SummaryVO();
		ReflectionTestUtils.setField(controller, "summaryService", summaryService);
		assertDoesNotThrow(() -> controller.updateASummary(summary));
		assertEquals(0,controller.updateASummary(summary).get("status"));
		
		Mockito.when(summaryService.updateSummary(summary)).thenReturn(1);
		ReflectionTestUtils.setField(controller, "summaryService", summaryService);
		assertDoesNotThrow(() -> controller.updateASummary(summary));
		assertEquals(1,controller.updateASummary(summary).get("status"));
	}
	
}

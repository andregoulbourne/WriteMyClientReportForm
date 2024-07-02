package com.andre.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.andre.model.SummaryVO;
import com.andre.service.SummaryService;
import com.andre.service.WriteCommentService;

class SummaryControllerTest {
	
	@InjectMocks
	private SummaryController controller;
	
	@Mock
	private WriteCommentService writeCommentService;
	
	@Mock
	private SummaryService summaryService;
	
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testGetAllSummarys() {
		ReflectionTestUtils.setField(controller, "summaryService", summaryService);
		assertDoesNotThrow(() -> controller.getAllSummarys());
	}
	
	
	private SummaryVO summary=new SummaryVO();
	
	@Test
	void testUpdateASummary() {
		ReflectionTestUtils.setField(controller, "summaryService", summaryService);
		assertDoesNotThrow(() -> controller.updateASummary(summary));
		assertEquals(0,controller.updateASummary(summary).get("status"));
		
		Mockito.when(summaryService.updateSummary(summary)).thenReturn(1);
		ReflectionTestUtils.setField(controller, "summaryService", summaryService);
		assertDoesNotThrow(() -> controller.updateASummary(summary));
		assertEquals(1,controller.updateASummary(summary).get("status"));
	}
	
	@Test
	void testAddASummary() {
		ReflectionTestUtils.setField(controller, "summaryService", summaryService);
		assertDoesNotThrow(() -> controller.addASummary(summary));
		assertEquals(0,controller.addASummary(summary).get("status"));
		
		Mockito.when(summaryService.addSummary(summary)).thenReturn(1);
		ReflectionTestUtils.setField(controller, "summaryService", summaryService);
		assertDoesNotThrow(() -> controller.addASummary(summary));
		assertEquals(1,controller.addASummary(summary).get("status"));
	}
	
	@Test
	void testDeleteASummary() {
		ReflectionTestUtils.setField(controller, "summaryService", summaryService);
		assertDoesNotThrow(() -> controller.deleteASummary("1"));
		assertEquals(0,controller.deleteASummary("1").get("status"));
		
		summary.setId("1");
		Mockito.when(summaryService.deleteSummary(Mockito.any())).thenReturn(1);
		ReflectionTestUtils.setField(controller, "summaryService", summaryService);
		assertDoesNotThrow(() -> controller.deleteASummary("1"));
	}
	
	@Test
	void testWriteAComment() {
		List<SummaryVO> list = new ArrayList<>();
		list.add(summary);
		
		ReflectionTestUtils.setField(controller, "writeCommentService", writeCommentService);
		assertDoesNotThrow(() -> controller.writeAComment(list));
	}
	
}

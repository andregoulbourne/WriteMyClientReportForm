package com.andre.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.andre.constants.Constants;
import com.andre.dao.SummaryDao;
import com.andre.model.SummaryVO;

class SummaryServiceTest {
	@InjectMocks
	private SummaryService service;
	@Mock
	private SummaryDao dao;
	
	private static final String ID = "1";
	private static final SummaryVO SUMMARY_VO = new SummaryVO();

	private AutoCloseable autoCloseable;

	@BeforeEach
	void setup() {
		autoCloseable = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	void close() throws Exception {
		autoCloseable.close();
	}
	
	@Test
	void testGetAllSummary(){
		List<SummaryVO> rs = new ArrayList<>();
		Mockito.when(dao.readCSVFile(null, Constants.ALL)).thenReturn(rs);
		
		assertEquals(rs,service.getAllSummary());
	}
	

	
	@Test
	void testGetASummary() {
		SummaryVO rs = new SummaryVO();
		Mockito.when(dao.readCSVFileSingleEntry(ID)).thenReturn(rs);
		
		assertEquals(rs,service.getASummary(ID));
	}

	@Test
	void testUpdateSummary_fail() {
		assertEquals(0,service.updateSummary(SUMMARY_VO));
	}
	@Test
	void testUpdateSummary_success() {
		Mockito.when(dao.csvFileEntry(SUMMARY_VO, Constants.UPDATE)).thenReturn(1);
		
		assertEquals(1,service.updateSummary(SUMMARY_VO));
	}
	
	@Test
	void testAddSummary() {
		Mockito.when(dao.csvFileEntry(SUMMARY_VO, Constants.ADD)).thenReturn(1);
		
		assertEquals(1,service.addSummary(SUMMARY_VO));
	}
	
	@Test
	void testDeleteSummary() {
		Mockito.when(dao.csvFileEntry(SUMMARY_VO, Constants.DELETE)).thenReturn(1);
		
		assertEquals(1,service.deleteSummary(SUMMARY_VO));
	}
	
	@Test
	void testGetValidationMsg() {
		String expectedMsg = "Expected Message";

		Mockito.when(dao.getValidationMsg())
			.thenReturn(expectedMsg);

		assertEquals(expectedMsg, service.getValidationMsg());
	}
}

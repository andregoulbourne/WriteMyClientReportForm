package com.andre.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
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
	
	private static final SummaryVO o = new SummaryVO();
	
	@Test
	void testUpdateSummary() {
		assertEquals(0,service.updateSummary(o));
		
		Mockito.when(dao.csvFileEntry(o, Constants.UPDATE)).thenReturn(1);
		
		assertEquals(1,service.updateSummary(o));
	}
	
	@Test
	void testAddSummary() {
		Mockito.when(dao.csvFileEntry(o, Constants.ADD)).thenReturn(1);
		
		assertEquals(1,service.addSummary(o));
	}
	
	@Test
	void testDeleteSummary() {
		Mockito.when(dao.csvFileEntry(o, Constants.DELETE)).thenReturn(1);
		
		assertEquals(1,service.deleteSummary(o));
	}
	
}

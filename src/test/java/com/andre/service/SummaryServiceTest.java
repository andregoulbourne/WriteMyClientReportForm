package com.andre.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.andre.constants.Constants;
import com.andre.dao.SummaryDao;
import com.andre.model.SummaryVO;

class SummaryServiceTest {
	
	private SummaryService service = new SummaryService();
	private SummaryDao dao;
	
	@BeforeEach
	void setup() {
		dao = Mockito.mock(SummaryDao.class);
	}
	
//	@Test
//	void tmp() {
//		try {
//			String currentPath = new java.io.File(".").getCanonicalPath();
//			System.out.println(currentPath);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		assertEquals(1, 1);
//	}
	
	@Test
	void testGetAllSummary(){
		List<SummaryVO> rs = new ArrayList<>();
		Mockito.when(dao.readCSVFile(null, Constants.ALL)).thenReturn(rs);
		ReflectionTestUtils.setField(service,"dao", dao); 
		
		assertEquals(rs,service.getAllSummary());
	}
	
	private static final String id = "1";
	
	@Test
	void testGetASummary() {
		SummaryVO rs = new SummaryVO();
		Mockito.when(dao.readCSVFileSingleEntry(id)).thenReturn(rs);
		ReflectionTestUtils.setField(service,"dao", dao); 
		
		assertEquals(rs,service.getASummary(id));
	}
	
	private static final SummaryVO o = new SummaryVO();
	
	@Test
	void testUpdateSummary() {
		
		ReflectionTestUtils.setField(service,"dao", dao);
		
		assertEquals(0,service.updateSummary(o));
		
		Mockito.when(dao.csvFileEntry(o, Constants.UPDATE)).thenReturn(1);
		ReflectionTestUtils.setField(service,"dao", dao); 
		
		assertEquals(1,service.updateSummary(o));
	}
	
	@Test
	void testAddSummary() {
		Mockito.when(dao.csvFileEntry(o, Constants.ADD)).thenReturn(1);
		ReflectionTestUtils.setField(service,"dao", dao); 
		
		assertEquals(1,service.addSummary(o));
	}
	
	@Test
	void testDeleteSummary() {
		Mockito.when(dao.csvFileEntry(o, Constants.DELETE)).thenReturn(1);
		ReflectionTestUtils.setField(service,"dao", dao); 
		
		assertEquals(1,service.deleteSummary(o));
	}
	
	@Test
	void testUtilityClassConstants() {
		Assertions.assertThrows(Exception.class,() -> Constants.test());
	}
}

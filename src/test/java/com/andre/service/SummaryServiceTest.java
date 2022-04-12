package com.andre.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

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
		Mockito.when(dao.readCSVFile()).thenReturn(rs);
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
		Mockito.when(dao.updateCSVFile(o)).thenReturn(1);
		ReflectionTestUtils.setField(service,"dao", dao); 
		
		assertEquals(1,service.updateSummary(o));
	}
	
	@Test
	void testAddSummary() {
		Mockito.when(dao.addCSVFileEntry(o)).thenReturn(1);
		ReflectionTestUtils.setField(service,"dao", dao); 
		
		assertEquals(1,service.addSummary(o));
	}
	
	@Test
	void testDeleteSummary() {
		Mockito.when(dao.deleteCSVFileEntry(o)).thenReturn(1);
		ReflectionTestUtils.setField(service,"dao", dao); 
		
		assertEquals(1,service.deleteSummary(o));
	}
}

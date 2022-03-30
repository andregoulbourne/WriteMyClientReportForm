package com.andre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.dao.SummaryDao;
import com.andre.model.SummaryObjectVO;

@Service
public class SummaryService {
	
	@Autowired
	private SummaryDao dao;
	
	public List<SummaryObjectVO> getAllSummary(){
		return dao.readCSVFile();
	}
	
	public SummaryObjectVO getASummary(String id) {
		return dao.readCSVFileSingleEntry(id);
	}
	
	public int updateSummary(SummaryObjectVO o) {
		return dao.updateCSVFile(o);
	}
	
	public int addSummary(SummaryObjectVO o) {
		return dao.addCSVFileEntry(o);
	}
	
	public int deleteSummary(SummaryObjectVO o) {
		return dao.deleteCSVFileEntry(o);
	}
}

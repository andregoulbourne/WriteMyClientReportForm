package com.andre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.dao.SummaryDao;
import com.andre.model.SummaryVO;

@Service
public class SummaryService {
	
	@Autowired
	private SummaryDao dao;
	
	public List<SummaryVO> getAllSummary(){
		return dao.readCSVFile();
	}
	
	public SummaryVO getASummary(String id) {
		return dao.readCSVFileSingleEntry(id);
	}
	
	public int updateSummary(SummaryVO o) {
		return dao.updateCSVFile(o);
	}
	
	public int addSummary(SummaryVO o) {
		return dao.addCSVFileEntry(o);
	}
	
	public int deleteSummary(SummaryVO o) {
		return dao.deleteCSVFileEntry(o);
	}
}

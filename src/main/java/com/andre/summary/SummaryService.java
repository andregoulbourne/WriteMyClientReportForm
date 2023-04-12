package com.andre.summary;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.utility.Constants;

@Service
public class SummaryService {
	
	@Autowired
	private SummaryDao dao;
	
	private static final Logger logger = Logger.getLogger(SummaryService.class);
	
	private void setDataPath() {
		try {
			String currentPath = new java.io.File(".").getCanonicalPath();
			dao.setFileInPath(currentPath+"/src/main/resources/Summarys.csv");
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public List<SummaryVO> getAllSummary(){
		setDataPath();
		return dao.readCSVFile(null, Constants.ALL);
	}
	
	public SummaryVO getASummary(String id) {
		setDataPath();
		return dao.readCSVFileSingleEntry(id);
	}
	
	public int updateSummary(SummaryVO o) {
		setDataPath();
		return dao.csvFileEntry(o, Constants.UPDATE);
	}
	
	public int addSummary(SummaryVO o) {
		setDataPath();
		return dao.csvFileEntry(o, Constants.ADD);
	}
	
	public int deleteSummary(SummaryVO o) {
		setDataPath();
		return dao.csvFileEntry(o, Constants.DELETE);
	}
	
	public String getValidationMsg() {
		return dao.getValidationMsg();
	}
	
	public void setValidationMsg(String in) {
		dao.setValidationMsg(in);
	}
}

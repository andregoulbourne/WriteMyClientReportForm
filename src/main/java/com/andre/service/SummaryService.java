package com.andre.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.constants.Constants;
import com.andre.dao.SummaryDao;
import com.andre.model.SummaryVO;

@Service
public class SummaryService {
	
	@Autowired
	public SummaryService(SummaryDao dao) {
		this.dao=dao;
	}
	
	private SummaryDao dao;
	
	private static final Logger logger = LoggerFactory.getLogger(SummaryService.class);
	
	private void setDataPath() {
		try {
			String currentPath = new java.io.File(".").getCanonicalPath();
			dao.setFileInPath(currentPath+"/src/main/resources/Summarys.csv");
		} catch (IOException e) {
			logger.error(Constants.EXCEPTION, e);
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

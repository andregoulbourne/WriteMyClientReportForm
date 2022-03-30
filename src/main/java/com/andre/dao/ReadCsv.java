package com.andre.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.andre.model.SummaryObjectVO;

public class ReadCsv {
	
	private static final Logger logger = Logger.getLogger(ReadCsv.class);
	
	private String fileInPath;
	
	public void setFileInPath(String fileInPath) {
		this.fileInPath = fileInPath;
	}
	
	public List<SummaryObjectVO> readCSVFile() {
		
		try (var br = new BufferedReader( new FileReader(fileInPath))) {
			var rs = new ArrayList<SummaryObjectVO>();
			
			String sCurrentLine="";
			var firstRow = true;
			while((sCurrentLine = br.readLine()) != null) {
				if(!firstRow) {
				logger.info(String.format("current Line in readCSVFile Is ... %s",sCurrentLine));
				
				var valuesList = split(sCurrentLine);
				
				var object = listToObject(valuesList);
				
				rs.add(object);
				
				} else firstRow = false;
				
			}
			return rs;
		} catch(Exception e) {
			logger.error("An Exception occured ...", e);
		}
		return new ArrayList<>();
	}
	
	private List<String> split(String in){
		var storedResult = new StringBuilder();
		var valuesList = new ArrayList<String>();
		var regular = true;
		for(var i=0; i<in.length(); i++) {
			if((in.charAt(i)!=',' && in.charAt(i)!='\"' && regular) || 
			(in.charAt(i)!='\"' && !regular))  storedResult.append(in.charAt(i));
			if(in.charAt(i)=='\"') regular = !regular;
			if((in.charAt(i)==',' || i==in.length()-1) && regular) {
				valuesList.add(storedResult.toString());
				storedResult = new StringBuilder();
			}
		}
		logger.info(String.format("valueList Once splited is ... %s",valuesList.toString()));
		return valuesList;
	}
	
	private SummaryObjectVO summaryObject;
	
	private SummaryObjectVO listToObject(List<String> list) {
		summaryObject = new SummaryObjectVO();
		for(var i=0; i<list.size(); i++) {
			var a = i+1;
			if(a==1) {
				summaryObject.setStudent(list.get(i));
				summaryObject.setId(String.valueOf(a));
			} else if(a==2) {
				summaryObject.setStatus(list.get(i));
			} else if(a==3) {
				setMadeDifference(list.get(i),a);
			} else if(a==4) {
				summaryObject.setCoveredValue(list.get(i));
			} else if(a==5) {
				summaryObject.setRecomendation(list.get(i));
			} else if(a==6) {
				setGender(list.get(i),a);
			}
		}
		logger.info("List to object is ... ");
		return summaryObject;
	}
	
	private void setMadeDifference(String in, int a) {
		if(in.equals("1")) summaryObject.setMadeADifference(true);
		if(in.equals("0")) summaryObject.setMadeADifference(false);
	}

	private void setGender(String in, int a) {
		if(in.equalsIgnoreCase("M")) summaryObject.setGender("he");
		if(in.equalsIgnoreCase("F")) summaryObject.setGender("she");
	}
}

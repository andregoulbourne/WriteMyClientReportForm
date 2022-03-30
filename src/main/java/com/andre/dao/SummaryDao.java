package com.andre.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.andre.model.SummaryObjectVO;

public class SummaryDao{
	
	private static final Logger logger = Logger.getLogger(SummaryDao.class);
	
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
	
	public SummaryObjectVO readCSVFileSingleEntry(String id) {
		
		try (var br = new BufferedReader( new FileReader(fileInPath))) {
			var rs = new SummaryObjectVO();
			
			String sCurrentLine="";
			var firstRow = true;
			while((sCurrentLine = br.readLine()) != null) {
				if(!firstRow) {
				
				var valuesList = split(sCurrentLine);
				
				var object = listToObject(valuesList);
				
					if(object.getId().equals(id)) {
						logger.info(String.format("current Line in readCSVFileSingleEntry Is ... %s",sCurrentLine));
						return object;
					}
				
				} else firstRow = false;
				
			}
		} catch(Exception e) {
			logger.error("An Exception occured ...", e);
		}
		return new SummaryObjectVO();
	}
	
	//Makes a new string for the Entry to be written to the Print Writer
	public String getAnEntry(SummaryObjectVO o) {
		try {
			var list = objectToList(o);
			return unsplit(list);
		} catch(Exception e) {
			logger.error("An Exception occurred ...", e);
		}
		return "";
	}
	
	public int updateCSVFile(String id, SummaryObjectVO o) {
		try {
			var rs = updateCSVFilePart1(id, o);
			return updateCSVPart2(rs);
		} catch(Exception e) {
			logger.error("An unexpected exception occured ", e);
			return 0;
		}
	}
	
	private List<SummaryObjectVO> updateCSVFilePart1(String id, SummaryObjectVO o) {
		
		if(id==null || o==null) return new ArrayList<>();
		
		try (var br = new BufferedReader( new FileReader(fileInPath))) {
			var rs = new ArrayList<SummaryObjectVO>();
			
			String sCurrentLine="";
			var firstRow = true;
			while((sCurrentLine = br.readLine()) != null) {
				if(!firstRow) {
				logger.info(String.format("current Line in readCSVFile Is ... %s",sCurrentLine));
				
				var valuesList = split(sCurrentLine);
				
				var object = listToObject(valuesList);
				
					if(object.getId().equals(id)) {
						logger.info(String.format("current Line in readCSVFileSingleEntry Is ... %s",sCurrentLine));
						var rsObject = updateObjectNullFields(object, o);
						rs.add(rsObject);
					} else {
						rs.add(object);
					}
				
				} else firstRow = false;
				
			}
			return rs;
		} catch(Exception e) {
			logger.error("An Exception occured ...", e);
		}
		return new ArrayList<>();
	}
	
	private SummaryObjectVO updateObjectNullFields(SummaryObjectVO existing, SummaryObjectVO rs) {
		try {
			if(rs.getId()==null) rs.setId(existing.getId());
			if(rs.getCoveredValue()==null) rs.setCoveredValue(existing.getCoveredValue());
			if(rs.getGender()==null) rs.setGender(existing.getGender());
			if(rs.getRecomendation()==null) rs.setRecomendation(existing.getRecomendation());
			if(rs.getStatus()==null) rs.setStatus(existing.getStatus());
			if(rs.getStudent()==null) rs.setStudent(existing.getStudent());
			logger.info("new object "+rs.toString());
			return rs;
		} catch(Exception e) {
			logger.error("An unexpected exception occurred",e);
			return new SummaryObjectVO();
		}
	}
	
	private static final String HEADER = "ID,STUDENT_NAME,STATUS,MADE_A_DIFFERENCE,COVERED_VALUE,RECOMMENDATION,GENDER";
	
	private int updateCSVPart2(List<SummaryObjectVO> list) {
		var rs = new ArrayList<String>();
		
		if(list.isEmpty() || fileInPath==null) {
			logger.error("dependency missing");
			return 0;
		}
		
		for(SummaryObjectVO o: list) {
			var entryString=getAnEntry(o);
			if(!entryString.isEmpty()) rs.add(entryString);
		}
		
		try(var writer = new PrintWriter(fileInPath,"UTF-8")) {
			writer.println(HEADER);
			for(String s: rs) {
				writer.println(s);
			}
			logger.info("Finished Updating CSV file ... ");
			return 1;
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			logger.error(e);
		}
		return 0;
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
	
	private String unsplit(List<String> in) {
		var rs = new StringBuilder();
		for(String s: in) {
			rs.append("\""+s+"\",");
		}
		rs.deleteCharAt(rs.length()-1);
		return rs.toString();
	}
	
	private SummaryObjectVO summaryObject;
	
	private SummaryObjectVO listToObject(List<String> list) {
		summaryObject = new SummaryObjectVO();
		for(var i=0; i<list.size(); i++) {
			var a = i+1;
			if(a==1) {
				summaryObject.setId(list.get(i));
			}else if(a==2) {
				summaryObject.setStudent(list.get(i));
			} else if(a==3) {
				summaryObject.setStatus(list.get(i));
			} else if(a==4) {
				setMadeDifference(list.get(i),a);
			} else if(a==5) {
				summaryObject.setCoveredValue(list.get(i));
			} else if(a==6) {
				summaryObject.setRecomendation(list.get(i));
			} else if(a==7) {
				setGender(list.get(i),a);
			}
		}
		logger.info("List to object is ... ");
		return summaryObject;
	}
	
	private List<String> objectToList(SummaryObjectVO o){
		var rs = new ArrayList<String>();
		rs.add(o.getId());
		rs.add(o.getStudent());
		rs.add(o.getStatus());
		if(o.isMadeADifference()) rs.add("1");
		else rs.add("0");
		rs.add(o.getCoveredValue());
		rs.add(o.getRecomendation());
		if(o.getGender().equals("he")) rs.add("M");
		else if(o.getGender().equals("she")) rs.add("F"); 
		
		return rs;
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

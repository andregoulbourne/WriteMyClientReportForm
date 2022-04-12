package com.andre.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.andre.model.SummaryVO;

@Repository
public class SummaryDao{
	
	private static final Logger logger = Logger.getLogger(SummaryDao.class);
	
	private String fileInPath;
	
	public void setFileInPath(String fileInPath) {
		this.fileInPath = fileInPath;
	}
	
	private String validationMsg;
	
	public String getValidationMsg() {
		return validationMsg;
	}
	
	public void setValidationMsg(String validationMsg) {
		this.validationMsg = validationMsg;
	}

	private static final String CURRENTLINE = "current Line Is ... %s";
	
	public List<SummaryVO> readCSVFile() {
		
		try (BufferedReader br = new BufferedReader( new FileReader(fileInPath))) {
			List<SummaryVO> rs = new ArrayList<>();
			
			String sCurrentLine="";
			boolean firstRow = true;
			while((sCurrentLine = br.readLine()) != null) {
				if(!firstRow) {
				logger.info(String.format(CURRENTLINE,sCurrentLine));
				
				List<String> valuesList = split(sCurrentLine);
				
				SummaryVO object = listToObject(valuesList);
				
				rs.add(object);
				
				} else firstRow = false;
				
			}
			return rs;
		} catch(Exception e) {
			logger.error(EXCEPTION, e);
		}
		return new ArrayList<>();
	}
	
	
	public SummaryVO readCSVFileSingleEntry(String id) {
		
		try (BufferedReader br = new BufferedReader( new FileReader(fileInPath))) {
			String sCurrentLine="";
			boolean firstRow = true;
			while((sCurrentLine = br.readLine()) != null) {
				if(!firstRow) {
				
				List<String> valuesList = split(sCurrentLine);
				
				SummaryVO object = listToObject(valuesList);
				
					if(object.getId().equals(id)) {
						logger.info(String.format(CURRENTLINE,sCurrentLine));
						return object;
					}
				
				} else firstRow = false;
				
			}
		} catch(Exception e) {
			logger.error(EXCEPTION, e);
		}
		return new SummaryVO();
	}
	
	private static final String EXCEPTION = "An Exception occured ...";
	
	public int updateCSVFile(SummaryVO o) {
		try {
			List<SummaryVO> rs = updateCSVFilePart1(o);
			return updateCSVPart2(rs);
		} catch(Exception e) {
			logger.error(EXCEPTION, e);
			return 0;
		}
	}
	
	public int addCSVFileEntry(SummaryVO o) {
		try {
			List<SummaryVO> rs = addCSVFileEntryPart1(o);
			return updateCSVPart2(rs);
		} catch(Exception e) {
			logger.error(EXCEPTION, e);
			return 0;
		}
	}
	
	public int deleteCSVFileEntry(SummaryVO o) {
		try {
			List<SummaryVO> rs = deleteCSVFileEntryPart1(o);
			return updateCSVPart2(rs);
		} catch(Exception e) {
			logger.error(EXCEPTION, e);
			return 0;
		}
	}
	
	private List<SummaryVO> updateCSVFilePart1(SummaryVO o) {
		String id = o.getId();
		if(id==null) return new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader( new FileReader(fileInPath))) {
			List<SummaryVO> rs = new ArrayList<>();
			
			String sCurrentLine="";
			boolean firstRow = true;
			while((sCurrentLine = br.readLine()) != null) {
				if(!firstRow) {
				logger.info(String.format(CURRENTLINE,sCurrentLine));
				
				List<String> valuesList = split(sCurrentLine);
				
				SummaryVO object = listToObject(valuesList);
				
					if(object.getId().equals(id)) {
						logger.info(String.format(CURRENTLINE,sCurrentLine));
						SummaryVO rsObject = updateObjectNullFields(object, o);
						rs.add(rsObject);
					} else {
						rs.add(object);
					}
				
				} else firstRow = false;
				
			}
			return rs;
		} catch(Exception e) {
			logger.error(EXCEPTION, e);
		}
		return new ArrayList<>();
	}
	
	private List<SummaryVO> addCSVFileEntryPart1(SummaryVO o) {
		String id = o.getId();
		if(id==null) return new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader( new FileReader(fileInPath))) {
			List<SummaryVO> rs = new ArrayList<>();
			
			String sCurrentLine="";
			boolean firstRow = true;
			while((sCurrentLine = br.readLine()) != null) {
				if(!firstRow) {
				logger.info(String.format(CURRENTLINE,sCurrentLine));
				
				List<String> valuesList = split(sCurrentLine);
				
				SummaryVO object = listToObject(valuesList);
				
					if(object.getId().equals(id)) {
						logger.info(String.format(CURRENTLINE,sCurrentLine));
						validationMsg = "Summary already exist ...";
						logger.info(validationMsg);
						return new ArrayList<>();
					} else {
						rs.add(object);
					}
				
				} else firstRow = false;
				
			}
			
			rs.add(o);
			return rs;
		} catch(Exception e) {
			logger.error(EXCEPTION, e);
		}
		return new ArrayList<>();
	}
	
	private List<SummaryVO> deleteCSVFileEntryPart1(SummaryVO o) {
		String id = o.getId();
		if(id==null) return new ArrayList<>();
		SummaryVO entry=readCSVFileSingleEntry(id);
		
		if(entry.getId()==null) return new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader( new FileReader(fileInPath))) {
			List<SummaryVO> rs = new ArrayList<>();
			
			String sCurrentLine="";
			boolean firstRow = true;
			while((sCurrentLine = br.readLine()) != null) {
				if(!firstRow) {
				logger.info(String.format(CURRENTLINE,sCurrentLine));
				
				List<String> valuesList = split(sCurrentLine);
				
				SummaryVO object = listToObject(valuesList);
				
					if(!object.getId().equals(id)) {
						rs.add(object);
					}
				
				} else firstRow = false;
				
			}
			return rs;
		} catch(Exception e) {
			logger.error(EXCEPTION, e);
		}
		return new ArrayList<>();
	}
	
	//Makes a new string for the Entry to be written to the Print Writer
	public String getAnEntry(SummaryVO o) {
		try {
			List<String> list = objectToList(o);
			return unsplit(list);
		} catch(Exception e) {
			logger.error(EXCEPTION, e);
		}
		return "";
	}
	
	private SummaryVO updateObjectNullFields(SummaryVO existing, SummaryVO rs) {
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
			logger.error(EXCEPTION,e);
			return new SummaryVO();
		}
	}
	
	private static final String HEADER = "ID,STUDENT_NAME,STATUS,MADE_A_DIFFERENCE,COVERED_VALUE,RECOMMENDATION,GENDER";
	
	private int updateCSVPart2(List<SummaryVO> list) {
		List<String> rs = new ArrayList<>();
		
		if(list.isEmpty() || fileInPath==null) {
			logger.error("dependency missing");
			return 0;
		}
		
		for(SummaryVO o: list) {
			String entryString=getAnEntry(o);
			if(!entryString.isEmpty()) rs.add(entryString);
		}
		
		try(PrintWriter writer = new PrintWriter(fileInPath,"UTF-8")) {
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
		StringBuilder storedResult = new StringBuilder();
		List<String> valuesList = new ArrayList<>();
		boolean regular = true;
		for(int i=0; i<in.length(); i++) {
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
		StringBuilder rs = new StringBuilder();
		for(String s: in) {
			rs.append("\""+s+"\",");
		}
		rs.deleteCharAt(rs.length()-1);
		return rs.toString();
	}
	
	private SummaryVO summaryObject;
	
	private SummaryVO listToObject(List<String> list) {
		summaryObject = new SummaryVO();
		for(int i=0; i<list.size(); i++) {
			int a = i+1;
			if(a==1) {
				summaryObject.setId(list.get(i));
			}else if(a==2) {
				summaryObject.setStudent(list.get(i));
			} else if(a==3) {
				summaryObject.setStatus(list.get(i));
			} else if(a==4) {
				setMadeDifference(list.get(i));
			} else if(a==5) {
				summaryObject.setCoveredValue(list.get(i));
			} else if(a==6) {
				summaryObject.setRecomendation(list.get(i));
			} else if(a==7) {
				setGender(list.get(i));
			}
		}
		logger.info("List to object is ... ");
		return summaryObject;
	}
	
	private List<String> objectToList(SummaryVO o){
		List<String> rs = new ArrayList<>();
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
	
	private void setMadeDifference(String in) {
		if(in.equals("1")) summaryObject.setMadeADifference(true);
		if(in.equals("0")) summaryObject.setMadeADifference(false);
	}

	private void setGender(String in) {
		if(in.equalsIgnoreCase("M")) summaryObject.setGender("he");
		if(in.equalsIgnoreCase("F")) summaryObject.setGender("she");
	}
}

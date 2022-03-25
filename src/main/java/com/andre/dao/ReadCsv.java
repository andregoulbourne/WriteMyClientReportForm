package com.andre.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.andre.model.SummaryObjectVO;

@Component
public class ReadCsv {
	
	private SummaryObjectVO summaryObject;
	
	public List<SummaryObjectVO> readCSVFile() {
		try (var br = new BufferedReader( new FileReader("./src/main/resources/Summarys.csv"))) {
			var rs = new ArrayList<SummaryObjectVO>();
			
			String sCurrentLine="";
			var firstRow = true;
			while((sCurrentLine = br.readLine()) != null) {
				if(!firstRow) {
				System.out.println(sCurrentLine);
				
				var valuesList = split(sCurrentLine);
				
				var object = listToObject(valuesList);
				
				rs.add(object);
				
				} else firstRow = false;
				
			}
			return rs;
		} catch(Exception e) {
			System.out.println("An Exception occured "+ e);
		}
		return null;
	}
	
	private List<String> split(String in){
		var fill = false;
		var storedResult = new StringBuilder();
		var valuesList = new ArrayList<String>();
		for(var i=0; i<in.length(); i++) {
			if(fill && in.charAt(i)!=',' && in.charAt(i)!='\"')  storedResult.append(in.charAt(i));
			if(in.charAt(i)==',' && !fill) fill = true; 
			if(in.charAt(i)==',' && fill) {
				valuesList.add(storedResult.toString());
				storedResult = new StringBuilder();
				fill = false; 
			}
		}
		return valuesList;
	}
	
	private SummaryObjectVO listToObject(List<String> list) {
		summaryObject = new SummaryObjectVO();
		for(var i=0; i<list.size(); i++) {
			var a = i+1;
			if(a==1) {
				summaryObject.setStudent(list.get(i));
			}else if(a==2) {
				summaryObject.setStatus(list.get(i));
			}else if(a==3) {
				if(list.get(i).equals("1")) summaryObject.setMadeADifference(true);
				if(list.get(i).equals("0")) summaryObject.setMadeADifference(false);
			}else if(a==4) {
				summaryObject.setCoveredValue(list.get(i));
			}
		}
		
		return summaryObject;
	}

}

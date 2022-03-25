package com.andre.model;

import java.util.Map;

public class SummaryObjectVO {
	
	private String id;
	
	private String student;
	
	//The students feelings or read on the students level in the subject
	private int statusStart;
	
	private Map<Integer,String> status;


	private boolean madeADifference;
	
	
	private String coveredKey;
	
	private String coveredValue;
	
	
	private int recomendation;
	
	private Map<Integer,String> mapRecommendations;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}
	
	public Map<Integer, String> getStatus() {
		return status;
	}

	public void setStatus(Map<Integer, String> status) {
		this.status = status;
	}

	public void setStatusStart(int statusStart) {
		this.statusStart = statusStart;
	}
	

	public int getStatusStart() {
		return statusStart;
	}

	public boolean isMadeADifference() {
		return madeADifference;
	}

	public void setMadeADifference(boolean madeADifference) {
		this.madeADifference = madeADifference;
	}

	public String getCoveredKey() {
		return coveredKey;
	}

	public void setCoveredKey(String coveredKey) {
		this.coveredKey = coveredKey;
	}

	public String getCoveredValue() {
		return coveredValue;
	}

	public void setCoveredValue(String coveredValue) {
		this.coveredValue = coveredValue;
	}

	public int getRecomendation() {
		return recomendation;
	}

	public void setRecomendation(int recomendation) {
		this.recomendation = recomendation;
	}

	public Map<Integer, String> getMapRecommendations() {
		return mapRecommendations;
	}

	public void setMapRecommendations(Map<Integer, String> mapRecommendations) {
		this.mapRecommendations = mapRecommendations;
	}

}

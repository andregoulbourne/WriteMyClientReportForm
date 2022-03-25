package com.andre.model;

import java.util.Map;

public class SummaryObjectVO {
	
	private String id;
	
	private String student;
	
	private String subject;
	
	//The students feelings or read on the students level in the subject
	private String statusStart;
	
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStatusStart() {
		return statusStart;
	}

	public void setStatusStart(String statusStart) {
		this.statusStart = statusStart;
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

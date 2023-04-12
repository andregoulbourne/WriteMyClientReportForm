package com.andre.summary;

public class SummaryVO {
	
	private String id;
	
	private String student;
	
	//The students feelings or read on the students level in the subject
	private String status;


	private boolean madeADifference;
	
	private String coveredValue;
	
	private String recomendation;
	
	private String gender;
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isMadeADifference() {
		return madeADifference;
	}

	public void setMadeADifference(boolean madeADifference) {
		this.madeADifference = madeADifference;
	}

	public String getCoveredValue() {
		return coveredValue;
	}

	public void setCoveredValue(String coveredValue) {
		this.coveredValue = coveredValue;
	}

	public String getRecomendation() {
		return recomendation;
	}

	public void setRecomendation(String recomendation) {
		this.recomendation = recomendation;
	}

	@Override
	public String toString() {
		return "SummaryObjectVO [id=" + id + ", student=" + student + ", status=" + status + ", madeADifference="
				+ madeADifference + ", coveredValue=" + coveredValue + ", recomendation=" + recomendation + ", gender="
				+ gender + "]";
	}

	

}

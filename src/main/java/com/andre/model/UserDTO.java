package com.andre.model;

public class UserDTO {

	private int id;

	private String username;

	private String pwd;

	private String firstName;

	private String lastName;

	private String middleName;

	private String emailId;

	private String phone;
	
	private boolean admin;
	
	private boolean vendor;

	public UserDTO(String username, String pwd, String firstName, String lastName, String middleName, String phone) {
		this.username = username;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.phone = phone;
	}
	
	public UserDTO() {
		super();
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isVendor() {
		return vendor;
	}

	public void setVendor(boolean vendor) {
		this.vendor = vendor;
	}

	public UserDTO(int id, String username, String pwd, String firstName, String lastName, String middleName,
			String emailId, String phone, boolean admin, boolean vendor) {
		super();
		this.id = id;
		this.username = username;
		this.pwd = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.emailId = emailId;
		this.phone = phone;
		this.admin = admin;
		this.vendor = vendor;
	}

}

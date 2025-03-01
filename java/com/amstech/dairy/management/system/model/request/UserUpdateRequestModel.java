package com.amstech.dairy.management.system.model.request;

import java.util.Date;

public class UserUpdateRequestModel {

	private int id;
	private String firstName;
	private String lastName;
    private String gender;
	private String email;
	private String mobileNumber;
	private Date dateOfBirth;
	private boolean isEmailVarified;
	private boolean isEmailUpdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public boolean isEmailVarified() {
		return isEmailVarified;
	}
	public void setEmailVarified(boolean isEmailVarified) {
		this.isEmailVarified = isEmailVarified;
	}
	public boolean isEmailUpdate() {
		return isEmailUpdate;
	}
	public void setEmailUpdate(boolean isEmailUpdate) {
		this.isEmailUpdate = isEmailUpdate;
	}
	
}

package com.amstech.dairy.management.system.model.request;

import java.util.Date;

public class UserModelRequest {

	private int id;
    private String email;
    private String firstName;
    private String gender;
   
    private String lastName;
    private String mobileNumber;
    private String password;
    private double adharNumber;
    private String aplodAdharImage;
    private Date dateOfBirth;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getAdharNumber() {
		return adharNumber;
	}
	public void setAdharNumber(double adharNumber) {
		this.adharNumber = adharNumber;
	}
	public String getAplodAdharImage() {
		return aplodAdharImage;
	}
	public void setAplodAdharImage(String aplodAdharImage) {
		this.aplodAdharImage = aplodAdharImage;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
   
    
    
}

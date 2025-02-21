package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="adhar_number")
	private double adharNumber;

	@Column(name="aplod_adhar_image")
	private String aplodAdharImage;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	private String email;

	@Column(name="first_name")
	private String firstName;

	private String gender;
   
	@Id
	private int id;

	@Column(name="last_name")
	private String lastName;

	@Column(name="mobile_number")
	private String mobileNumber;

	private String password;

	@Lob
	private String usercol;

	public User() {
	}

	public double getAdharNumber() {
		return this.adharNumber;
	}

	public void setAdharNumber(double adharNumber) {
		this.adharNumber = adharNumber;
	}

	public String getAplodAdharImage() {
		return this.aplodAdharImage;
	}

	public void setAplodAdharImage(String aplodAdharImage) {
		this.aplodAdharImage = aplodAdharImage;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsercol() {
		return this.usercol;
	}

	public void setUsercol(String usercol) {
		this.usercol = usercol;
	}

}
package com.amstech.dairy.management.system.model.request;

import java.util.Date;

import lombok.Data;

@Data
public class UserSignupModelRequest {

	private int id;
	private String email;
	private String firstName;
	private String gender;

	private String lastName;
	private String mobileNumber;
	private String password;

	private Date dateOfBirth;

}

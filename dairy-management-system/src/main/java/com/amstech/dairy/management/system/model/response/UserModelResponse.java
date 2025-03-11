package com.amstech.dairy.management.system.model.response;

import java.sql.Date;

import lombok.Data;

@Data
public class UserModelResponse {
    
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private Date dateOfBirth;  // Ensure correct type
    private String gender;

  
}

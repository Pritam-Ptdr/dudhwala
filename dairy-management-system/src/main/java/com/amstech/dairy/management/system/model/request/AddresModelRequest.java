package com.amstech.dairy.management.system.model.request;

import lombok.Data;

@Data
public class AddresModelRequest {

	
	    private String countryName;
	    private String stateName;
	    private String cityName;
	    private Integer userId;
	    private String lendMark;
	    private Integer postalCode;
	    private String mapLocation;
	    private String nearBy;
		
	   
}

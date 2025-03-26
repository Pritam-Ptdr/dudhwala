package com.amstech.dairy.management.system.model.response;

import java.math.BigDecimal;
import java.sql.Date;

import com.amstech.dairy.management.system.entity.City;

import lombok.Data;

@Data
public class SubscriptionModelResponse {

	private Integer id;

	private Integer productId;
	private String productName;
	private int quantity;
	private BigDecimal price;

	private int packetSize;

	private Integer userId;

	private String schedule;

	private String planName;

	private Date deliveryStartDate;
	private Date deliveryEndDate;

	private BigDecimal totalPrice;
	
//	private String firstName;
//    private String lastName; 
	private String fullName;
	private String email;
	private String mobileNumber ;
	
	private String cityName;
	private String stateName;
	private String countryName;
	
	private String nearBy;
	private  int  postalCode ;
	
	private String address ;
	

}

package com.amstech.dairy.management.system.model.request;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;


@Data
public class SubcriptionModelRequest {

	
	private Integer id;

	private Integer productId;
	private String productName;
	private int quantity;
	private BigDecimal price;

	private double totalPrice;
	private int packetSize;

	private Integer userId;
	private String UserName;
	private Date deliveryStartDate;
	
	private Date deliveryEndtDate;
	private String  planName;
	
	private String status;

}

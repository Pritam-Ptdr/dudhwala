package com.amstech.dairy.management.system.model.response;

import java.math.BigDecimal;

import com.amstech.dairy.management.system.entity.Image;

import lombok.Data;


@Data
public class ProductResponseModel {

	 private int id;
	    private String description;
	    private BigDecimal price;
	    private String productName;
	    private int quantity;
	    private String stock;
	    private BigDecimal totalPrice;
	    private Integer imageId;
	    private int categoryId;
		
	   
		
}

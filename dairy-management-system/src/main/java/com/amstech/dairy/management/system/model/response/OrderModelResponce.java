package com.amstech.dairy.management.system.model.response;

import java.math.BigDecimal;
import java.sql.Date;

public class OrderModelResponce {
    private int id;
    private int packetSize;
    private BigDecimal price;  // Changed from DecimalFormat to BigDecimal
    private String productName;
    private int productId;
    private String delivarySchedule;
    private int quantity;
    private Date diliveryDate;
    private String userName;
    private int userId;

    // Getters and Setters
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPacketSize() {
		return packetSize;
	}

	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDelivarySchedule() {
		return delivarySchedule;
	}

	public void setDelivarySchedule(String delivarySchedule) {
		this.delivarySchedule = delivarySchedule;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDiliveryDate() {
		return diliveryDate;
	}

	public void setDiliveryDate(Date diliveryDate) {
		this.diliveryDate = diliveryDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

   
}

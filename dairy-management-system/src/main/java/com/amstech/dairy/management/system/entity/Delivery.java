package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the deliveries database table.
 * 
 */
@Entity
@Table(name="deliveries")
@NamedQuery(name="Delivery.findAll", query="SELECT d FROM Delivery d")
public class Delivery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="delivery_date")
	private Date deliveryDate;

	@Column(name="order_status")
	private String orderStatus;

	@Column(name="shiping_address_id")
	private int shipingAddressId;

	@Column(name="subcription_id")
	private int subcriptionId;

	@Column(name="supplier_id")
	private int supplierId;

	@Column(name="total_amount")
	private BigDecimal totalAmount;

	public Delivery() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getShipingAddressId() {
		return this.shipingAddressId;
	}

	public void setShipingAddressId(int shipingAddressId) {
		this.shipingAddressId = shipingAddressId;
	}

	public int getSubcriptionId() {
		return this.subcriptionId;
	}

	public void setSubcriptionId(int subcriptionId) {
		this.subcriptionId = subcriptionId;
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}
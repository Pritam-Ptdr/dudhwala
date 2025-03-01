package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the order_item database table.
 * 
 */
@Entity
@Table(name="order_item")
@NamedQuery(name="OrderItem.findAll", query="SELECT o FROM OrderItem o")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="order_itemcol")
	private String orderItemcol;

	private int price;

	private int quantity;

	//bi-directional many-to-one association to Order
	@ManyToOne
	private Order order;

	public OrderItem() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderItemcol() {
		return this.orderItemcol;
	}

	public void setOrderItemcol(String orderItemcol) {
		this.orderItemcol = orderItemcol;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
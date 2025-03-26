package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;




@Entity
@Table(name = "`order`") // ✅ Table name in the database
@NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o") // ✅ Correct entity name
public class Order implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // ✅ Define foreign key for user
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false) // ✅ Define foreign key for product
    private MilkProduct milkProduct;

    @Column(name = "order_date")
    private Date orderDate;

    private int quantity;
    private String schedule;
    
	@ManyToOne
	@JoinColumn(name = "address_id", nullable = true) // Ensure this column exists in DB
	private Address address;

    
    public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public Order() {
    }

   
   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public MilkProduct getMilkProduct() { return milkProduct; }
    public void setMilkProduct(MilkProduct milkProduct) { this.milkProduct = milkProduct; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }


	
   
}

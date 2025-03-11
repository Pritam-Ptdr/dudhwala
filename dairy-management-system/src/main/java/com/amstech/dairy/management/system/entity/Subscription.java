package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Where;

/**
 * The persistent class for the subscription database table.
 */
@Entity

@Table(name = "subscription") // Explicitly defining the table name
@NamedQuery(name = "Subscription.findAll", query = "SELECT s FROM Subscription s")
public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //
	private int id;

	@Column(name = "plan_name") // Corrected from "plane_name"
	private String planName;

	private BigDecimal price;

//    @Column(name = "product_id", nullable = false)
//    private int productId;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "status", length = 100)
	private String status;

	@Column(name = "timeschedule", length = 100)
	private String timeSchedule;

	@Column(name = "quantity")
	private Integer quantity; // Made Integer to allow null values

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; // Assuming you have a User entity
	
	@ManyToOne
	@JoinColumn(name = "address_id", nullable = true) // Ensure this column exists in DB
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private MilkProduct milkProduct;

 // Corrected Reference to MilkProduct

	public MilkProduct getMilkProduct() {
		return milkProduct;
	}

	public void setMilkProduct(MilkProduct milkProduct) {
		this.milkProduct = milkProduct;
	}

	// Bi-directional one-to-many relationship with SubscriptionHistory
	@OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SubscriptionHistory> subscriptionHistories;
	
	@Column(name = "total_price")
	private BigDecimal totalPrice;

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Subscription() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

//    public int getProductId() {
//        return productId;
//    }

//    public void setProductId(int productId) {
//        this.productId = productId;
//    }

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimeSchedule() {
		return timeSchedule;
	}

	public void setTimeSchedule(String timeSchedule) {
		this.timeSchedule = timeSchedule;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SubscriptionHistory> getSubscriptionHistories() {
		return subscriptionHistories;
	}

	public void setSubscriptionHistories(List<SubscriptionHistory> subscriptionHistories) {
		this.subscriptionHistories = subscriptionHistories;
	}

	public SubscriptionHistory addSubscriptionHistory(SubscriptionHistory subscriptionHistory) {
		getSubscriptionHistories().add(subscriptionHistory);
		subscriptionHistory.setSubscription(this);
		return subscriptionHistory;
	}

	public SubscriptionHistory removeSubscriptionHistory(SubscriptionHistory subscriptionHistory) {
		getSubscriptionHistories().remove(subscriptionHistory);
		subscriptionHistory.setSubscription(null);
		return subscriptionHistory;
	}
}

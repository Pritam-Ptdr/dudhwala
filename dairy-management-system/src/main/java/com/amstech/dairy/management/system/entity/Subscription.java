package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the subscription database table.
 * 
 */
@Entity
@NamedQuery(name="Subscription.findAll", query="SELECT s FROM Subscription s")
public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="plane_name")
	private String planeName;

	private BigDecimal price;

	@Column(name="product_id")
	private int productId;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to SubscriptionHistory
	@OneToMany(mappedBy="subscription")
	private List<SubscriptionHistory> subscriptionHistories;

	public Subscription() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPlaneName() {
		return this.planeName;
	}

	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<SubscriptionHistory> getSubscriptionHistories() {
		return this.subscriptionHistories;
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
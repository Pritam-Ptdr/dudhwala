package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the payment database table.
 * 
 */
@Entity
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="customer_id")
	private int customerId;
    
	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="payment_date")
	private Date paymentDate;

	@Column(name="payment_status")
	private String paymentStatus;

	//bi-directional many-to-one association to PaymentMode
	@ManyToOne
	@JoinColumn(name="payment_mode_id")
	private PaymentMode paymentMode;

	public Payment() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public PaymentMode getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

}
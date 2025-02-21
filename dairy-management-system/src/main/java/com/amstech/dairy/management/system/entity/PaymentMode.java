package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the payment_mode database table.
 * 
 */
@Entity
@Table(name="payment_mode")
@NamedQuery(name="PaymentMode.findAll", query="SELECT p FROM PaymentMode p")
public class PaymentMode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="payment_mode")
	private String paymentMode;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="paymentMode")
	private List<Payment> payments;

	public PaymentMode() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setPaymentMode(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setPaymentMode(null);

		return payment;
	}

}
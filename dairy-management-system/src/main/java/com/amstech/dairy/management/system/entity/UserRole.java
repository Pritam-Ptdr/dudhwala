package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to Milkmen
	@OneToMany(mappedBy="userRole")
	private List<Milkmen> milkmens;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="userRole")
	private List<Notification> notifications;

	//bi-directional many-to-one association to SubscriptionHistory
	@OneToMany(mappedBy="userRole")
	private List<SubscriptionHistory> subscriptionHistories;

	//bi-directional many-to-one association to Role
	@ManyToOne
	private Role role;

	public UserRole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Milkmen> getMilkmens() {
		return this.milkmens;
	}

	public void setMilkmens(List<Milkmen> milkmens) {
		this.milkmens = milkmens;
	}

	public Milkmen addMilkmen(Milkmen milkmen) {
		getMilkmens().add(milkmen);
		milkmen.setUserRole(this);

		return milkmen;
	}

	public Milkmen removeMilkmen(Milkmen milkmen) {
		getMilkmens().remove(milkmen);
		milkmen.setUserRole(null);

		return milkmen;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setUserRole(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setUserRole(null);

		return notification;
	}

	public List<SubscriptionHistory> getSubscriptionHistories() {
		return this.subscriptionHistories;
	}

	public void setSubscriptionHistories(List<SubscriptionHistory> subscriptionHistories) {
		this.subscriptionHistories = subscriptionHistories;
	}

	public SubscriptionHistory addSubscriptionHistory(SubscriptionHistory subscriptionHistory) {
		getSubscriptionHistories().add(subscriptionHistory);
		subscriptionHistory.setUserRole(this);

		return subscriptionHistory;
	}

	public SubscriptionHistory removeSubscriptionHistory(SubscriptionHistory subscriptionHistory) {
		getSubscriptionHistories().remove(subscriptionHistory);
		subscriptionHistory.setUserRole(null);

		return subscriptionHistory;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
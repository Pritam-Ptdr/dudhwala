package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the milkmen database table.
 * 
 */
@Entity
@NamedQuery(name="Milkmen.findAll", query="SELECT m FROM Milkmen m")
public class Milkmen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="assigned_area")
	private String assignedArea;

	@Column(name="assigned_order")
	private int assignedOrder;

	private String rating;

	@Column(name="total_order")
	private int totalOrder;

	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JoinColumn(name="user_role_id")
	private UserRole userRole;

	//bi-directional many-to-one association to Route
	@OneToMany(mappedBy="milkmen")
	private List<Route> routes;

	public Milkmen() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssignedArea() {
		return this.assignedArea;
	}

	public void setAssignedArea(String assignedArea) {
		this.assignedArea = assignedArea;
	}

	public int getAssignedOrder() {
		return this.assignedOrder;
	}

	public void setAssignedOrder(int assignedOrder) {
		this.assignedOrder = assignedOrder;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getTotalOrder() {
		return this.totalOrder;
	}

	public void setTotalOrder(int totalOrder) {
		this.totalOrder = totalOrder;
	}

	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public List<Route> getRoutes() {
		return this.routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public Route addRoute(Route route) {
		getRoutes().add(route);
		route.setMilkmen(this);

		return route;
	}

	public Route removeRoute(Route route) {
		getRoutes().remove(route);
		route.setMilkmen(null);

		return route;
	}

}
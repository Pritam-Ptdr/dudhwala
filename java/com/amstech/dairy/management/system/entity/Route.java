package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the routes database table.
 * 
 */
@Entity
@Table(name="routes")
@NamedQuery(name="Route.findAll", query="SELECT r FROM Route r")
public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="route_name")
	private String routeName;

	//bi-directional many-to-one association to Milkmen
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Milkmen milkmen;

	public Route() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRouteName() {
		return this.routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Milkmen getMilkmen() {
		return this.milkmen;
	}

	public void setMilkmen(Milkmen milkmen) {
		this.milkmen = milkmen;
	}

}
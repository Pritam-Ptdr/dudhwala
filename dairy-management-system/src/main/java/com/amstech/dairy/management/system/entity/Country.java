package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Ensure ID is auto-generated
	private int id;

	private String name;

	// Default constructor (required by JPA)
	public Country() {}

	// Constructor for new country (without ID, since it's auto-generated)
	public Country(String name) {
		this.name = name;
	}

	// Constructor with ID
	public Country(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

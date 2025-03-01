package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

/**
 * The persistent class for the country database table.
 */
@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Ensure ID is auto-generated
	private int id;

	@Column(nullable = false, unique = true) // Ensures country names are unique and not null
	private String name;

	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<State> states;  // One Country can have multiple States

	// Default constructor (Required by JPA)
	public Country() {}

	// Constructor for new country creation
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

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + "]";
	}
}

package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Ensure ID is auto-generated
    private int id;

    @Column(nullable = false, unique = true) // Ensures city name is unique and cannot be null
    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)  // Foreign Key Mapping to State
    private State state;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;  // One City can have multiple Addresses

    public City() {}

    public City(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public City(int id, String name, State state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

   
}

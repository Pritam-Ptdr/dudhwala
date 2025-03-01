package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

/**
 * The persistent class for the state database table.
 */
@Entity
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private int id;

    @Column(nullable = false, unique = true) // Ensures no duplicate state names
    private String name;

    @ManyToOne
    @JoinColumn(name="country_id", nullable = false)  // Foreign Key, cannot be null
    private Country country;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<City> cities;  // One State has multiple Cities

    // Default constructor (Required by JPA)
    public State() {}

    // Constructor for new State creation
    public State(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    // Constructor with ID (Optional, for updating)
    public State(int id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}


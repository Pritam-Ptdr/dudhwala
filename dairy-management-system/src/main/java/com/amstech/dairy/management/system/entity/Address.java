package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the address database table.
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="lend_mark")
    private String lendMark;

    @Lob
    @Column(name="map_location")
    private String mapLocation;

    @Column(name="near_by")
    private String nearBy;

    @Column(name="postal_code")
    private int postalCode;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)  // Foreign Key to City
    private City city;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Foreign Key to User
    private User user;  // Renamed from userId to user

    public Address() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLendMark() {
        return this.lendMark;
    }

    public void setLendMark(String lendMark) {
        this.lendMark = lendMark;
    }

    public String getMapLocation() {
        return this.mapLocation;
    }

    public void setMapLocation(String mapLocation) {
        this.mapLocation = mapLocation;
    }

    public String getNearBy() {
        return this.nearBy;
    }

    public void setNearBy(String nearBy) {
        this.nearBy = nearBy;
    }

    public int getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public User getUser() {  // Fixed method name
        return user;
    }

    public void setUser(User user) {  // Fixed method name
        this.user = user;
    }
}
package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="city_id")
	private int cityId;

	
	@Id
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

	@Column(name="user_role_id")
	private int userRoleId;

	public Address() {
	}

	public int getCityId() {
		return this.cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
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

	public int getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

}
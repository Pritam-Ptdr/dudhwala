package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the image database table.
 * 
 */
@Entity
@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	@Column(name="image_url")
	private String imageUrl;

	//bi-directional many-to-one association to MilkProduct
	@OneToMany(mappedBy="image")
	private List<MilkProduct> milkProducts;

	public Image() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<MilkProduct> getMilkProducts() {
		return this.milkProducts;
	}

	public void setMilkProducts(List<MilkProduct> milkProducts) {
		this.milkProducts = milkProducts;
	}

	public MilkProduct addMilkProduct(MilkProduct milkProduct) {
		getMilkProducts().add(milkProduct);
		milkProduct.setImage(this);

		return milkProduct;
	}

	public MilkProduct removeMilkProduct(MilkProduct milkProduct) {
		getMilkProducts().remove(milkProduct);
		milkProduct.setImage(null);

		return milkProduct;
	}

}
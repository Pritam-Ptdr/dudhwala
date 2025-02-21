package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the product_categories database table.
 * 
 */
@Entity
@Table(name="product_categories")
@NamedQuery(name="ProductCategory.findAll", query="SELECT p FROM ProductCategory p")
public class ProductCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to MilkProduct
	@OneToMany(mappedBy="productCategory")
	private List<MilkProduct> milkProducts;

	public ProductCategory() {
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

	public List<MilkProduct> getMilkProducts() {
		return this.milkProducts;
	}

	public void setMilkProducts(List<MilkProduct> milkProducts) {
		this.milkProducts = milkProducts;
	}

	public MilkProduct addMilkProduct(MilkProduct milkProduct) {
		getMilkProducts().add(milkProduct);
		milkProduct.setProductCategory(this);

		return milkProduct;
	}

	public MilkProduct removeMilkProduct(MilkProduct milkProduct) {
		getMilkProducts().remove(milkProduct);
		milkProduct.setProductCategory(null);

		return milkProduct;
	}

}
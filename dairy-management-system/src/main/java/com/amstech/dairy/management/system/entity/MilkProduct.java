package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the milk_product database table.
 * 
 */
@Entity
@Table(name="milk_product")
@NamedQuery(name="MilkProduct.findAll", query="SELECT m FROM MilkProduct m")
public class MilkProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String description;

	private int paketsize;

	private BigDecimal price;

	@Column(name="product_name")
	private String productName;

	private int quantity;

	@Lob
	private String stock;

	@Column(name="total_price")
	private BigDecimal totalPrice;

	//bi-directional many-to-one association to Image
	@ManyToOne
	@JoinColumn(name="product_image_url_id")
	private Image image;

	//bi-directional many-to-one association to ProductCategory
	@ManyToOne
	@JoinColumn(name="product_categories_id")
	private ProductCategory productCategory;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="milkProduct")
	private List<Order> orders;

	public MilkProduct() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPaketsize() {
		return this.paketsize;
	}

	public void setPaketsize(int paketsize) {
		this.paketsize = paketsize;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStock() {
		return this.stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ProductCategory getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setMilkProduct(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setMilkProduct(null);

		return order;
	}

}
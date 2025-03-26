package com.amstech.dairy.management.system.model.request;

import java.math.BigDecimal;

import com.amstech.dairy.management.system.entity.MilkProduct;

import lombok.Data;


@Data
public class ProductModelRequest {

	
	 private int id;
	    private String description;
	    private BigDecimal price;
	    private String productName;
	    private int quantity;
	    private String stock;
	    private BigDecimal totalPrice;
	    private int imageId;
	    private int categoryId;
	    private String filePath;
	    
	    
	    public ProductModelRequest(MilkProduct milkProduct) {
	        this.id = milkProduct.getId();
	        this.description = milkProduct.getDescription();
	        this.price = milkProduct.getPrice();
	        this.productName = milkProduct.getProductName();
	        this.quantity = milkProduct.getQuantity();
	        this.stock = milkProduct.getStock();
	        this.totalPrice = milkProduct.getTotalPrice();
	        this.imageId = milkProduct.getImage() != null ? milkProduct.getImage().getId() : 0; // Handle null
	        this.categoryId = milkProduct.getProductCategory() != null ? milkProduct.getProductCategory().getId() : 0; // Handle null
	        this.filePath = milkProduct.getFilePath();
	    }
	    
	    
	    
//	    public int getId() {
//			return id;
//		}
//		public void setId(int id) {
//			this.id = id;
//		}
//		public String getDescription() {
//			return description;
//		}
//		public void setDescription(String description) {
//			this.description = description;
//		}
//		public BigDecimal getPrice() {
//			return price;
//		}
//		public void setPrice(BigDecimal price) {
//			this.price = price;
//		}
//		public String getProductName() {
//			return productName;
//		}
//		public void setProductName(String productName) {
//			this.productName = productName;
//		}
//		public int getQuantity() {
//			return quantity;
//		}
//		public void setQuantity(int quantity) {
//			this.quantity = quantity;
//		}
//		public String getStock() {
//			return stock;
//		}
//		public void setStock(String stock) {
//			this.stock = stock;
//		}
//		public BigDecimal getTotalPrice() {
//			return totalPrice;
//		}
//		public void setTotalPrice(BigDecimal totalPrice) {
//			this.totalPrice = totalPrice;
//		}
//		
//		public int getCategoryId() {
//			return categoryId;
//		}
//		public int getImageId() {
//			return imageId;
//		}
//		public void setImageId(int imageId) {
//			this.imageId = imageId;
//		}
//		public void setCategoryId(int categoryId) {
//			this.categoryId = categoryId;
//		}
//		
		
	    
	    
}

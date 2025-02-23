package com.amstech.dairy.management.system.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.dairy.management.system.controller.ProductController;
import com.amstech.dairy.management.system.entity.Image;
import com.amstech.dairy.management.system.entity.MilkProduct;
import com.amstech.dairy.management.system.entity.ProductCategory;
import com.amstech.dairy.management.system.entity.User;
import com.amstech.dairy.management.system.model.request.ProductModelRequest;
import com.amstech.dairy.management.system.repo.ImageRepo;
import com.amstech.dairy.management.system.repo.ProductCategoryRepo;
import com.amstech.dairy.management.system.repo.ProductRepo;

@Service
public class ProductService {
	
	
	    
	    @Autowired
	    public ProductRepo productRepo;
	    
	    @Autowired
	    public ProductCategoryRepo productCategoryRepo;
	    
	    @Autowired
	    public ImageRepo imageRepo;
	    
	    public void addMilkProduct(ProductModelRequest productModelRequest) {
	        if (productModelRequest.getProductName() == null || productModelRequest.getProductName().isEmpty()) {
	            throw new IllegalArgumentException("Product name is required");
	        }

	        if (productModelRequest.getPrice() == null || productModelRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
	            throw new IllegalArgumentException("Price must be greater than zero");
	        }

	        Optional<ProductCategory> categoryOpt = productCategoryRepo.findById(productModelRequest.getCategoryId());
	        if (categoryOpt.isEmpty()) {
	            throw new IllegalArgumentException("Invalid product category ID");
	        }

	        Optional<Image> imageOpt = imageRepo.findById(productModelRequest.getImageId());
	        

	        // Creating the MilkProduct entity
	        MilkProduct milkProduct = new MilkProduct();
	        milkProduct.setProductName(productModelRequest.getProductName());
	        milkProduct.setStock(productModelRequest.getStock());
	        milkProduct.setPrice(productModelRequest.getPrice());
	        milkProduct.setTotalPrice(productModelRequest.getTotalPrice());
	        milkProduct.setDescription(productModelRequest.getDescription());
	        milkProduct.setQuantity(productModelRequest.getQuantity());
	        milkProduct.setProductCategory(categoryOpt.get());
	        milkProduct.setImage(imageOpt.get());

	        MilkProduct savedProduct = productRepo.save(milkProduct);
	        
}
}

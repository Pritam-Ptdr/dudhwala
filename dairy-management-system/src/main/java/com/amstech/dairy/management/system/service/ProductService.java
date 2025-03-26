package com.amstech.dairy.management.system.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.dairy.management.system.controller.ProductController;
import com.amstech.dairy.management.system.entity.Image;
import com.amstech.dairy.management.system.entity.MilkProduct;
import com.amstech.dairy.management.system.entity.ProductCategory;
import com.amstech.dairy.management.system.entity.User;
import com.amstech.dairy.management.system.model.request.ProductModelRequest;
import com.amstech.dairy.management.system.model.request.UserUpdateRequestModel;
import com.amstech.dairy.management.system.model.response.ProductResponseModel;
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

	public ProductModelRequest addMilkProduct(ProductModelRequest productModelRequest) {
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

	    // Creating and saving the MilkProduct entity
	    MilkProduct milkProduct = new MilkProduct();
	    milkProduct.setProductName(productModelRequest.getProductName());
	    milkProduct.setStock(productModelRequest.getStock());
	    milkProduct.setPrice(productModelRequest.getPrice());
	    milkProduct.setTotalPrice(productModelRequest.getTotalPrice());
	    milkProduct.setDescription(productModelRequest.getDescription());
	    milkProduct.setQuantity(productModelRequest.getQuantity());
	    milkProduct.setProductCategory(categoryOpt.get());
	    milkProduct.setImage(imageOpt.orElse(null)); // Handle possible null

	    MilkProduct savedProduct = productRepo.save(milkProduct);

	    // Convert `MilkProduct` back to `ProductModelRequest`
	    return new ProductModelRequest(savedProduct);
	}

	public List<ProductResponseModel> findAllProduct() throws Exception {
		List<MilkProduct> productList = productRepo.findAll();
		List<ProductResponseModel> productResponseModels = new ArrayList<>();

		for (MilkProduct milkProduct : productList) {
			ProductResponseModel responseModel = new ProductResponseModel();

			responseModel.setId(milkProduct.getId());
			responseModel.setProductName(milkProduct.getProductName());
			responseModel.setPrice(milkProduct.getPrice());
			responseModel.setStock(milkProduct.getStock());
			responseModel.setDescription(milkProduct.getDescription());
			responseModel.setTotalPrice(milkProduct.getTotalPrice());
			responseModel.setQuantity(milkProduct.getQuantity());

			ProductCategory category = milkProduct.getProductCategory();
			if (category != null) {

				responseModel.setCategoryId(category.getId());
			} else {
				throw new Exception("ProductCategory not found for product id: " + milkProduct.getId());
			}

			Image image = milkProduct.getImage();
			if (image != null) {

				//responseModel.setImageId(image.getId());
			//	responseModel.setImageId(image.getId());
			} else {
				throw new Exception("Image not found for product id: " + milkProduct.getId());
			}

			productResponseModels.add(responseModel);
		}

		return productResponseModels;
	}


	
	public void updateProduct(ProductModelRequest productModelRequest) throws Exception {

		Optional<MilkProduct> userOptional = productRepo.findById(productModelRequest.getId());
		Optional<ProductCategory> categoryOpt = productCategoryRepo.findById(productModelRequest.getCategoryId());
		Optional<Image> imageOpt = imageRepo.findById(productModelRequest.getImageId());
		

		if (!userOptional.isPresent()) {
			throw new Exception("User does not exist ");
		}
		
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
	
	public String deleteByProductId(Integer id) throws Exception {
	    Optional<MilkProduct> productOptional = productRepo.findById(id);

	    if (!productOptional.isPresent()) {
	        throw new Exception("product does not exist...");
	    }

	    productRepo.deleteById(id);  

	    return "Product deleted successfully.";
	}
	
	public ProductResponseModel findProductById(Integer id) throws Exception {
	    
	    if (id == null) {
	        throw new IllegalArgumentException("Product ID must not be null");
	    }

	    
	    System.out.println("Fetching product with ID: " + id);

	    // Fetch the MilkProduct from the database
	    Optional<MilkProduct> productOptional = productRepo.findById(id);

	    if (!productOptional.isPresent()) {
	        throw new Exception("Product with ID " + id + " does not exist in the database.");
	    }

	    MilkProduct milkProduct = productOptional.get();
	    System.out.println("Product found: " + milkProduct.getProductName());

	    // Create the response model and set product details
	    ProductResponseModel responseModel = new ProductResponseModel();
	    responseModel.setId(milkProduct.getId());
	    responseModel.setPrice(milkProduct.getPrice());
	    responseModel.setProductName(milkProduct.getProductName());
	    responseModel.setStock(milkProduct.getStock());
	    responseModel.setDescription(milkProduct.getDescription());
	    responseModel.setTotalPrice(milkProduct.getTotalPrice());
	    responseModel.setQuantity(milkProduct.getQuantity());

	    return responseModel;
	}

}

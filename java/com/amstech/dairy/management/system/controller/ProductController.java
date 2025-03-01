package com.amstech.dairy.management.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.dairy.management.system.model.request.ProductModelRequest;
import com.amstech.dairy.management.system.model.response.ProductResponseModel;
import com.amstech.dairy.management.system.service.ProductService;

@Controller
@RestController
@RequestMapping("/product")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	public ProductService productService;

	public ProductController() {
		this.productService = productService;
		LOGGER.info("ProductController : is Created");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addMilkProduct", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addMilkProduct(@RequestBody ProductModelRequest productModelRequest) {

		LOGGER.info(" AddMilkProduct : object created ");

		try {
			productService.addMilkProduct(productModelRequest);
			return new ResponseEntity<Object>(" Product add successfuly" + productModelRequest.getId(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(" Product add faild" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findAllProduct", produces = "application/json")
	public ResponseEntity<Object> findAllProduct() {
		LOGGER.info("Find By AllProduct : Object Created");

		try {
			List<ProductResponseModel> productResponseModels = productService.findAllProduct();
			return new ResponseEntity<>(productResponseModels, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error fetching all Milk Product " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updateProduct", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateProduct(@RequestBody ProductModelRequest productModelRequest) {

		LOGGER.info("Upadte User All Details {}", productModelRequest.getId());
		
		try {
			productService.updateProduct(productModelRequest);
			return new ResponseEntity<Object>(" Product update successfuly" + productModelRequest, HttpStatus.OK);

		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<Object>("Error Product update faild" + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteByProduct", produces = "application/json")
	public ResponseEntity<Object> deleteByProduct(@RequestParam("id") Integer id) {
    
	LOGGER.info(" DELETE BY PRODUCT : OBJECT IS CRITED");	
		try {
			productService.deleteByProductId(id);
			return new ResponseEntity<Object>("User delete Successfully", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("faild user deleted", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/findProductById", produces = "application/json")
	public ResponseEntity<Object> findProductById(@RequestParam("id") Integer id) {

		//System.out.println("User is find in this id" + id);
		LOGGER.info("PRODUCT IS FINND bY ID OBJECT IS CREATED {}",id );

		try {
			ProductResponseModel productResponseModel = productService.findProductById(id);
			return new ResponseEntity<Object>(productResponseModel, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(" Error fetching Product by id " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}

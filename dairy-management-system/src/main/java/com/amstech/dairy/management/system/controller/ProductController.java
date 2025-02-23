package com.amstech.dairy.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.dairy.management.system.model.request.ProductModelRequest;
import com.amstech.dairy.management.system.service.ProductService;

@Controller
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	public ProductService productService;

	public ProductController() {
		this.productService = productService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addMilkProduct", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addMilkProduct(@RequestBody ProductModelRequest productModelRequest) {

		System.out.println("Object created : project controller ");

		try {
             productService.addMilkProduct(productModelRequest);
			return new ResponseEntity<Object>(" Product add successfuly" + productModelRequest.getId(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(" Product add faild" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}

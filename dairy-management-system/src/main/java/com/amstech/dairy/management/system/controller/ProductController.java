package com.amstech.dairy.management.system.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amstech.dairy.management.system.model.request.ProductModelRequest;
import com.amstech.dairy.management.system.model.response.ProductResponseModel;
import com.amstech.dairy.management.system.responce.RestResponce;
import com.amstech.dairy.management.system.service.FileService;
import com.amstech.dairy.management.system.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RestController
@RequestMapping("/product")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	public FileService fileService;

	@Autowired
	public ObjectMapper objectMapper;

	@Autowired
	public ProductService productService;

	public ProductController() {
		this.productService = productService;
		LOGGER.info("ProductController : is Created");
	}

//	@RequestMapping(method = RequestMethod.POST, value = "/addMilkProduct", consumes = "application/json", produces = "application/json")
//	public ResponseEntity<Map<String, Object>> addMilkProduct(@RequestBody ProductModelRequest productModelRequest) {
//
//		LOGGER.info(" AddMilkProduct : object created ");
//
//		try {
//			productService.addMilkProduct(productModelRequest);
//			return ResponseEntity.ok(RestResponce.build().data(productModelRequest.getProductName()).success(200)
//					.message("User Created Successfully").getResponse());
//		} catch (Exception e) {
//			LOGGER.error("Error during signup {}", e.getMessage(), e);
//
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(RestResponce.build().error(404).message("User signup failed").getResponse());
//		}
//	}
	@PostMapping(value = "/addMilkProduct", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<Map<String, Object>> addMilkProduct(
            @RequestParam("productRequestModelJson") String productRequestModelJson,
            @RequestParam(value = "profilePhotofile", required = false) MultipartFile profilePhotoFile) {

        String filePath = null;
        LOGGER.info("Received request to add milk product");

        try {
            // Deserialize JSON string to ProductModelRequest
            ProductModelRequest productModelRequest = objectMapper.readValue(productRequestModelJson, ProductModelRequest.class);

            LOGGER.info("Saving product: {}", productModelRequest.getProductName());

            // Handle file upload
            if (profilePhotoFile != null && !profilePhotoFile.isEmpty()) {
                LOGGER.info("Processing file: {} (Size: {} bytes)", profilePhotoFile.getOriginalFilename(), profilePhotoFile.getSize());

                if (profilePhotoFile.getSize() > fileService.getFileMaxSize()) {
                    throw new IllegalArgumentException("File size exceeds the maximum limit of " + fileService.getFileMaxSize() + " bytes");
                }

                // Save file and get file path
                filePath = fileService.saveFile(profilePhotoFile.getBytes(), "product", FilenameUtils.getExtension(profilePhotoFile.getOriginalFilename()));
                productModelRequest.setFilePath(filePath);
            }

            // Save product in DB
            ProductModelRequest savedProduct = productService.addMilkProduct(productModelRequest);

            return ResponseEntity.ok(RestResponce.build()
                    .data(savedProduct.getProductName())
                    .success(200)
                    .message("Product Created Successfully")
                    .getResponse());

        } catch (Exception e) {
            LOGGER.error("Error adding milk product: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(RestResponce.build().error(500).message("Product creation failed").getResponse());
        }
    }

	@RequestMapping(method = RequestMethod.GET, value = "/findAllProduct", produces = "application/json")
	public ResponseEntity<Map<String, Object>> findAllProduct() {

		try {
			List<ProductResponseModel> productResponseModels = productService.findAllProduct();
			return ResponseEntity.ok(RestResponce.build()

					.success(200).message("User Created Successfully").getResponse());
		} catch (Exception e) {
			LOGGER.error("Error during signup {}", e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(RestResponce.build().error(404).message("User signup failed").getResponse());
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updateProduct", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateProduct(@RequestBody ProductModelRequest productModelRequest) {

		LOGGER.info("update User All details  {} ", productModelRequest.getId());

		try {
			productService.updateProduct(productModelRequest);
			return ResponseEntity.ok(RestResponce.build().data(productModelRequest.getProductName()).success(200)
					.message("User Created Successfully").getResponse());
		} catch (Exception e) {
			LOGGER.error("Error during signup {}", e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(RestResponce.build().error(404).message("User signup failed").getResponse());
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteByProduct", produces = "application/json")
	public ResponseEntity<Object> deleteByProduct(@RequestParam("id") Integer id) {

		try {
			productService.deleteByProductId(id);
			return ResponseEntity
					.ok(RestResponce.build().data(id).success(200).message("User Created Successfully").getResponse());
		} catch (Exception e) {
			LOGGER.error("Error during signup {}", e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(RestResponce.build().error(404).message("User signup failed").getResponse());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findProductById", produces = "application/json")
	public ResponseEntity<Map<String, Object>> findProductById(@RequestParam("id") Integer id) {

		LOGGER.info("User is find in this id {}", id);

		try {
			ProductResponseModel productResponseModel = productService.findProductById(id);
			return ResponseEntity.ok(RestResponce.build().data(productResponseModel).success(200)
					.message("User find Successfully").getResponse());
		} catch (Exception e) {
			LOGGER.error("Error during product find  {}", e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(RestResponce.build().error(404).message("User find by product failed").getResponse());
		}
	}
}

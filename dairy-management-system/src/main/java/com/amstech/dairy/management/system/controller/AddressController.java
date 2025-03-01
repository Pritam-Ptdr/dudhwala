package com.amstech.dairy.management.system.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.amstech.dairy.management.system.model.request.AddresModelRequest;
import com.amstech.dairy.management.system.service.AddressService;

@RestController
@RequestMapping("/addres")
public class AddressController {

    @Autowired
    private AddressService addresService; // Fixed: No need to reassign in constructor

    private final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

    public AddressController() {
        LOGGER.info("ADDRESS CONTROLLER : OBJECT CREATED");
    }

    @RequestMapping( method =RequestMethod.POST , value = "/addAddres", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> addAddres(@RequestBody AddresModelRequest addresModelRequest) {
        LOGGER.info("ADD ADDRESS : OBJECT CREATED");

        Map<String, Object> response = new HashMap<>();
        try {
            addresService.addAddress(addresModelRequest);
            response.put("status", "success");
            response.put("message", "User Address successfully added");
            response.put("data", addresModelRequest.getCityName());

            return ResponseEntity.ok(response); // Fixed: Use `ResponseEntity.ok()`
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Failed to save user address: {}", e.getMessage(), e);

            response.put("status", "error");
            response.put("message", "Failed to add user address: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}


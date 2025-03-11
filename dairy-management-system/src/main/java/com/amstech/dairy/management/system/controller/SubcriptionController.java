package com.amstech.dairy.management.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.amstech.dairy.management.system.model.request.SubcriptionModelRequest;
import com.amstech.dairy.management.system.model.response.SubscriptionModelResponse;
import com.amstech.dairy.management.system.model.response.UserModelResponse;
import com.amstech.dairy.management.system.service.SubscriptionService;

@Controller
@RestController
@RequestMapping("/subscription")
public class SubcriptionController {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SubcriptionController.class);

    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping(method = RequestMethod.POST, value = "/addSubscription", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> addSubscription(@RequestBody SubcriptionModelRequest subcriptionModelRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            subscriptionService.addSubscription(subcriptionModelRequest);
            response.put("status", "success");
            response.put("message", "Subscription added successfully");
            response.put("data", subcriptionModelRequest.getUserId());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Subscription Failed: {}", e.getMessage(), e);
            response.put("status", "error");
            response.put("message", "Subscription failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findBySubscriptionId", produces = "application/json")
    public ResponseEntity<Map<String, Object>> findBySubscriptionId(@RequestParam("id") Integer id) {
        LOGGER.info("Finding Subscription by ID: {}", id);
        Map<String, Object> response = new HashMap<>();

        try {
            SubscriptionModelResponse subscriptionModelResponse = subscriptionService.findBySubscriptionId(id);
            response.put("status", "success");
            response.put("message", "Subscription found successfully");
            response.put("data", subscriptionModelResponse);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error: Subscription Not Found {}", e.getMessage(), e);
            response.put("status", "error");
            response.put("message", "Subscription not found");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateSubscription", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> updateSubscription(@RequestBody SubcriptionModelRequest subcriptionModelRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            subscriptionService.updateSupcription(subcriptionModelRequest);
            response.put("status", "success");
            response.put("message", "Subscription updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Subscription Update Failed: {}", e.getMessage(), e);
            response.put("status", "error");
            response.put("message", "Subscription update failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteSubscription", produces = "application/json")
    public ResponseEntity<Map<String, Object>> deleteSubscription(@RequestParam("id") Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            String message = subscriptionService.deleteBySubscription(id);
            response.put("status", "success");
            response.put("message", message);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Subscription Deletion Failed: {}", e.getMessage(), e);
            response.put("status", "error");
            response.put("message", "Subscription deletion failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    
    @RequestMapping(method = RequestMethod.GET , value = "/findAllSubscription" ,produces = "application/json")
    public ResponseEntity<Map<String , Object>> findAllSubscription(@RequestParam ("page") Integer page ,@RequestParam ("size") Integer size ){
		
    	Map<String, Object> response = new HashMap<>();
    	
    	 try {
    		 List<SubscriptionModelResponse> subscResponseModel = subscriptionService.findByAllSubscription(page ,size);
    		 response.put("Data", subscResponseModel);
             response.put("status", "success");
             response.put("message", "Subscription findAll successfully");
             return ResponseEntity.ok(response);
         } catch (Exception e) {
             LOGGER.error("Subscription findAll Failed: {}", e.getMessage(), e);
             response.put("status", "error");
             response.put("message", "Subscription findAll failed");
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
         }
    	
    }
    
}

	


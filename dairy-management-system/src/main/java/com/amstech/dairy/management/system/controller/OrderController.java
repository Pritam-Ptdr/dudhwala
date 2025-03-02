package com.amstech.dairy.management.system.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

import com.amstech.dairy.management.system.entity.MilkProduct;
import com.amstech.dairy.management.system.model.request.OrderModelRequest;
import com.amstech.dairy.management.system.repo.OrderRepo;
import com.amstech.dairy.management.system.service.OrderService;

@Controller
@RestController
@RequestMapping("/order")
public class OrderController {

	private final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	public OrderService orderService;
	
	@Autowired
	public OrderController() {
	    this.orderService =  orderService ;
		LOGGER.info("ORDER SERVICE : OBJECT CREATED" );
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/addOrder" , consumes = "application/json" , produces = "application/json")
	public ResponseEntity<Map<String ,Object>> addOrder( @RequestBody OrderModelRequest orderModelRequest )throws Exception{
		
		Map<String ,Object> response = new HashMap<>();
		
		try {
			orderService.addOrder(orderModelRequest);
			response.put("status", "success");
			response.put("message", "add to order  successfuly");
			response.put("dat", orderModelRequest.getUserId() );
			
			return ResponseEntity.ok( response);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			LOGGER.error("ORDER FAILD {}", e.getMessage() ,e);
			
			response.put("status", "Error");
			response.put("message", "Order faild");
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE , value = "/deleteOrder" ,  produces = "application/json")
	public ResponseEntity<Map<String ,Object>> deleteOrder(@RequestParam("id") Integer id )throws Exception{
		
		Map<String ,Object> response = new HashMap<>();
		
		try {
			orderService.deleteByOrder(id);
			response.put("status", "success");
			response.put("message", "order remove  successfuly");
			
			
			return ResponseEntity.ok( response);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			LOGGER.error("ORDER FAILD {}", e.getMessage() ,e);
			
			response.put("status", "Error");
			response.put("message", "Order remove faild place try one more time");
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		
		
	}
	
	
	
	
	
}

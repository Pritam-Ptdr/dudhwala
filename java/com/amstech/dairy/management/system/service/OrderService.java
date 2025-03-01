package com.amstech.dairy.management.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.dairy.management.system.entity.MilkProduct;
import com.amstech.dairy.management.system.entity.Order;
import com.amstech.dairy.management.system.entity.User;
import com.amstech.dairy.management.system.model.request.OrderModelRequest;
import com.amstech.dairy.management.system.repo.OrderRepo;
import com.amstech.dairy.management.system.repo.ProductRepo;
import com.amstech.dairy.management.system.repo.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	public UserRepo userRepo;
	
	@Autowired
	public ProductRepo productRepo;
	
	@Autowired
	public OrderRepo orderRepo ;
	
	
	@Transactional
	public void addOrder(OrderModelRequest orderModelRequest) {
	    System.out.println("Received OrderModelRequest JSON: " + orderModelRequest);

	    // Debug User ID
	    System.out.println("Fetching user with ID: " + orderModelRequest.getUserId());

	    if (orderModelRequest.getUserId() == 0) {
	        throw new RuntimeException("Invalid User ID: 0");
	    }

	    User user = userRepo.findById(orderModelRequest.getUserId())
	        .orElseThrow(() -> new RuntimeException("User not found with ID: " +orderModelRequest.getUserId()));

	    // Fetch Product
	    MilkProduct milkProduct = productRepo.findById(orderModelRequest.getProductId())
	        .orElseThrow(() -> new RuntimeException("Product not found with ID: " + orderModelRequest.getProductId()));

	    // Create Order
	    Order order = new Order();
	    order.setUserId(orderModelRequest.getUserId());
	    order.setOrderDate(orderModelRequest.getDeliveryDate());
	    order.setSchedule(orderModelRequest.getDeliverySchedule());
	    order.setQuantity(orderModelRequest.getQuantity());

	    // Save Order
	    orderRepo.save(order);
	    System.out.println("Order saved successfully!");
	}

}

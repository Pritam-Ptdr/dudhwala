package com.amstech.dairy.management.system.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
	
	
	

	
	@Transactional
	public void addOrder(OrderModelRequest orderModelRequest) {
	    LOGGER.info("Processing Order for User ID: {}", orderModelRequest.getUserId());

	    
	    if (orderModelRequest.getUserId() == null || orderModelRequest.getUserId() <= 0) {
	        throw new IllegalArgumentException("Invalid User ID: " + orderModelRequest.getUserId());
	    }

	    
	    Optional<User> userOptional = userRepo.findById(orderModelRequest.getUserId());
	    if (userOptional.isEmpty()) {
	        LOGGER.error("No user found with ID: {}", orderModelRequest.getUserId());
	        throw new RuntimeException("User not found with ID: " + orderModelRequest.getUserId());
	    }
	    User user = userOptional.get();
	    LOGGER.info("User found: {}", user.getFirstName());

	    
	    MilkProduct milkProduct = productRepo.findById(orderModelRequest.getProductId())
	            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + orderModelRequest.getProductId()));

	    // Save Order
	    Order order = new Order();
	    order.setUser(user);
	    order.setMilkProduct(milkProduct);
	    order.setOrderDate(orderModelRequest.getDeliveryDate());
	    order.setSchedule(orderModelRequest.getDeliverySchedule());
	    order.setQuantity(orderModelRequest.getQuantity());

	    orderRepo.save(order);
	    LOGGER.info("Order saved successfully!");
	}

	
	public String deleteByOrder(Integer id) throws Exception {
	    Optional<Order> OrderOptional = orderRepo.findById(id);
	    
	    LOGGER.info("ORDER DELETE bY ID {}" , id);

	    if (!OrderOptional.isPresent()) {
	        throw new Exception("product does not exist...");
	    }

	    orderRepo.deleteById(id);  

	    return "order deleted successfully.";
	}

}

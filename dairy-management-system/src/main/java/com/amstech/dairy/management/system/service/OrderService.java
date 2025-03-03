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
import com.amstech.dairy.management.system.model.response.OrderModelResponce;
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
  
	//// ..............................order update .......................
	// data note given this code update a null value place read a code properly and update a api 

	@Transactional
	public void updateOrder(OrderModelRequest orderModelRequest) {
	    LOGGER.info("Processing Order update for Order ID: {}", orderModelRequest.getId());

	    if (orderModelRequest.getId() == null) {
	        throw new IllegalArgumentException("Order ID must not be null");
	    }

	    Order order = orderRepo.findById(orderModelRequest.getId())
	            .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderModelRequest.getId()));

	    
	    User user = userRepo.findById(orderModelRequest.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found with ID: " + orderModelRequest.getUserId()));

//	    // Fetch Product by Name
//	    MilkProduct milkProduct = productRepo.findByProductNameUser(orderModelRequest.getProductName())
//	            .orElseThrow(() -> new RuntimeException("Product not found with Name: " + orderModelRequest.getProductName()));

	    // Update Order details
	    order.setUser(user);
	   
	    
	    //order.setMilkProduct(milkProduct);
	    order.setOrderDate(orderModelRequest.getDeliveryDate());
	    order.setSchedule(orderModelRequest.getDeliverySchedule());
	    order.setQuantity(orderModelRequest.getQuantity());

	    orderRepo.save(order);
	    LOGGER.info("Order updated successfully!");
	}


	
	//// ..........................order Delete .........................................
	
	public String deleteByOrder(Integer id) throws Exception {
	    Optional<Order> OrderOptional = orderRepo.findById(id);
	    
	    LOGGER.info("ORDER DELETE bY ID {}" , id);

	    if (!OrderOptional.isPresent()) {
	        throw new Exception("product does not exist...");
	    }

	    orderRepo.deleteById(id);  

	    return "order deleted successfully.";
	}
	
	////................................order find gy id .......................................
	
	public OrderModelResponce orderFindById(Integer id) throws Exception {
	    LOGGER.info("Finding ORDER by ID: {}", id);

	    Optional<Order> orderOptional = orderRepo.findById(id);

	    if (orderOptional.isEmpty()) {
	        throw new Exception("Order does not exist...");
	    }

	    Order order = orderOptional.get();

	    // Fetch user details
	    Optional<User> userOptional = userRepo.findById(order.getUser().getId());
	    if (userOptional.isEmpty()) {
	        throw new Exception("User does not exist...");
	    }
	    User user = userOptional.get();

	    // Fetch product details
	    Optional<MilkProduct> productOptional = productRepo.findById(order.getMilkProduct().getId());
	    if (productOptional.isEmpty()) {
	        throw new Exception("Product does not exist...");
	    }
	    MilkProduct product = productOptional.get();

	    
	    OrderModelResponce orderResponse = new OrderModelResponce();
	    orderResponse.setId(order.getId());
	    orderResponse.setUserId(user.getId());
	    orderResponse.setUserName(user.getFirstName());
	    orderResponse.setProductId(product.getId());
	    orderResponse.setProductName(product.getProductName());
	    orderResponse.setPrice(product.getPrice());
	    orderResponse.setPacketSize(product.getPaketsize());
	    orderResponse.setQuantity(order.getQuantity());
	    orderResponse.setDiliveryDate(order.getOrderDate());
	    orderResponse.setDelivarySchedule(order.getSchedule());

	    return orderResponse;
	}


}

package com.amstech.dairy.management.system.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amstech.dairy.management.system.entity.Address;
import com.amstech.dairy.management.system.entity.City;
import com.amstech.dairy.management.system.entity.Country;
import com.amstech.dairy.management.system.entity.MilkProduct;
import com.amstech.dairy.management.system.entity.Order;
import com.amstech.dairy.management.system.entity.State;
import com.amstech.dairy.management.system.entity.Subscription;
import com.amstech.dairy.management.system.entity.User;
import com.amstech.dairy.management.system.model.request.SubcriptionModelRequest;
import com.amstech.dairy.management.system.model.response.SubscriptionModelResponse;
import com.amstech.dairy.management.system.repo.SubscriptionRepo;
import com.amstech.dairy.management.system.repo.UserRepo;
import com.amstech.dairy.management.system.repo.AddressRepo;
import com.amstech.dairy.management.system.repo.ProductRepo;

import jakarta.transaction.Transactional;

@Service
public class SubscriptionService {

    private final Logger LOGGER = LoggerFactory.getLogger(SubscriptionService.class);

    @Autowired
    public UserRepo userRepo;
    
    @Autowired
    public AddressRepo addressRepo;

    @Autowired
    public ProductRepo productRepo;

    @Autowired
    public SubscriptionRepo subscriptionRepo;

    @Transactional
    public void addSubscription(SubcriptionModelRequest subcriptionModelRequest) {
        LOGGER.info("Processing Subscription for User ID: {}", subcriptionModelRequest.getUserId());

        User user = userRepo.findById(subcriptionModelRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + subcriptionModelRequest.getUserId()));

        MilkProduct milkProduct = productRepo.findById(subcriptionModelRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + subcriptionModelRequest.getProductId()));

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setMilkProduct(milkProduct);
        subscription.setStartDate(subcriptionModelRequest.getDeliveryStartDate());
        subscription.setEndDate(subcriptionModelRequest.getDeliveryEndtDate());
        subscription.setQuantity(subcriptionModelRequest.getQuantity());
        subscription.setPlanName(subcriptionModelRequest.getPlanName());
        subscription.setPrice(subcriptionModelRequest.getPrice());
        subscription.setStatus(subcriptionModelRequest.getStatus());

        subscriptionRepo.save(subscription);
        LOGGER.info("Subscription saved successfully!");
    }

    @Transactional
    public void updateSupcription(SubcriptionModelRequest subcriptionModelRequest) {
        LOGGER.info("Updating Subscription with ID: {}", subcriptionModelRequest.getId());

        Subscription subscription = subscriptionRepo.findById(subcriptionModelRequest.getId())
                .orElseThrow(() -> new RuntimeException("Subscription not found with ID: " + subcriptionModelRequest.getId()));

        subscription.setQuantity(subcriptionModelRequest.getQuantity());
        subscription.setStartDate(subcriptionModelRequest.getDeliveryStartDate());
        subscription.setEndDate(subcriptionModelRequest.getDeliveryEndtDate());

        subscriptionRepo.save(subscription);
        LOGGER.info("Subscription updated successfully!");
    }

    public String deleteBySubscription(Integer id) throws Exception {
        LOGGER.info("Deleting Subscription with ID: {}", id);

        Subscription subscription = subscriptionRepo.findById(id)
                .orElseThrow(() -> new Exception("Subscription does not exist"));

        subscriptionRepo.deleteById(id);
        return "Subscription deleted successfully.";
    }
    
    public SubscriptionModelResponse findBySubscriptionId(Integer id) throws Exception {
        LOGGER.info("Fetching Subscription details for ID: {}", id);

        // Fetch subscription or throw an exception
        Subscription subscription = subscriptionRepo.findById(id)
                .orElseThrow(() -> new Exception("Subscription not found with ID: " + id));

        // Prepare response object
        SubscriptionModelResponse response = new SubscriptionModelResponse();
        response.setId(subscription.getId());
        response.setUserId(subscription.getUser().getId());
        response.setProductId(subscription.getMilkProduct().getId());
        response.setPlanName(subscription.getPlanName());
        response.setQuantity(subscription.getQuantity());
        response.setPrice(subscription.getPrice());
        response.setDeliveryStartDate((Date) subscription.getStartDate());
        response.setDeliveryEndDate((Date) subscription.getEndDate());
        response.setSchedule(subscription.getTimeSchedule()); // ✅ Fixed line

        return response;
    }
    
//    public List<SubscriptionModelResponse> findByAllSubscription(Integer page, Integer size) throws Exception {
//        Page<Subscription> subscriptionPage = subscriptionRepo.findAll(PageRequest.of(page, size));
//
//        List<Subscription> subscriptionList = subscriptionPage.getContent();
//
//        System.out.println("Fetched Subscriptions: " + subscriptionList.size()); // Debugging line
//
//        List<SubscriptionModelResponse> subscriptionModelResponse = new ArrayList<>();
//
//        for (Subscription subscription : subscriptionList) {
//            SubscriptionModelResponse response = new SubscriptionModelResponse();
//            response.setId(subscription.getId());
//            response.setProductName(subscription.getMilkProduct().getProductName());
//            response.setPrice(subscription.getMilkProduct().getPrice());
//
//            response.setUserId(subscription.getUser().getId());
//            response.setProductId(subscription.getMilkProduct().getId());
//            response.setPlanName(subscription.getPlanName());
//            response.setQuantity(subscription.getQuantity());
//            
//            response.setDeliveryStartDate((Date) subscription.getStartDate());
//            response.setDeliveryEndDate((Date) subscription.getEndDate());
//            response.setSchedule(subscription.getTimeSchedule());
//
//            subscriptionModelResponse.add(response);
//        }
//
//        System.out.println("Response Data Size: " + subscriptionModelResponse.size()); // Debugging line
//
//        return subscriptionModelResponse;
//    }

    public List<SubscriptionModelResponse> findByAllSubscription(Integer page, Integer size) throws Exception {
       
    	Page<Subscription> subscriptionPage = subscriptionRepo.findAll(PageRequest.of(page, size));
       
    	List<Subscription> subscriptionList = subscriptionPage.getContent();

        System.out.println("Fetched Subscriptions: " + subscriptionList.size()); // Debugging

        List<SubscriptionModelResponse> subscriptionModelResponse = new ArrayList<>();

        for (Subscription subscription : subscriptionList) {
            SubscriptionModelResponse response = new SubscriptionModelResponse();
           
            response.setId(subscription.getId());
            
            response.setProductName(subscription.getMilkProduct().getProductName());
            
            response.setPrice(subscription.getMilkProduct().getPrice());
            
            response.setUserId(subscription.getUser().getId());

            String firstName = subscription.getUser().getFirstName();
            String lastName = subscription.getUser().getLastName();
            String fullName = firstName + " " + lastName;
            response.setFullName(fullName);
            response.setEmail(subscription.getUser().getEmail());
            response.setMobileNumber(subscription.getUser().getMobileNumber());
            response.setProductId(subscription.getMilkProduct().getId());
            response.setPlanName(subscription.getPlanName());
            response.setQuantity(subscription.getQuantity());
            response.setDeliveryStartDate((Date) subscription.getStartDate());
            response.setDeliveryEndDate((Date) subscription.getEndDate());
            response.setSchedule(subscription.getTimeSchedule());

            // ✅ Fetch Address from User (if subscription address is NULL)
            Address address = subscription.getAddress();
            if (address == null) {
                List<Address> userAddresses = addressRepo.findByUserId(subscription.getUser().getId()); // Fetch all user addresses
                if (!userAddresses.isEmpty()) {
                    address = userAddresses.get(0); // Take first address (or implement a better selection logic)
                }
            }
               
            response.setPostalCode(address.getPostalCode());
            response.setNearBy(address.getNearBy());
            
               
            if (address != null) {
                City city = address.getCity();
                if (city != null) {
                    response.setCityName(city.getName());
                //	String cityName =  city.getName();
                    

                    State state = city.getState();
                    if (state != null) {
                       response.setStateName(state.getName());
                    	//String   StateName = state.getName();

                        Country country = state.getCountry();
                        if (country != null) {
                           response.setCountryName(country.getName());
                          //   String countryName  = country.getName();
                            
                        } else {
                            response.setCountryName("N/A");
                        }
                    } else {
                        response.setStateName("N/A");
                    }
                } else {
                    response.setCityName("N/A");
                }
            } else {
                response.setCityName("N/A");
                response.setStateName("N/A");
                response.setCountryName("N/A");
            }
            
            
       
            // Calculate total price
            BigDecimal price = subscription.getMilkProduct().getPrice();
            Integer quantity = subscription.getQuantity();
            String planName = subscription.getPlanName();
            BigDecimal totalPrice = calculateTotalPrice(price, quantity, planName);
            response.setTotalPrice(totalPrice);

            subscriptionModelResponse.add(response);
        }

        LOGGER.info("Response Data Size:{} ", subscriptionModelResponse.size());
        return subscriptionModelResponse;
    }


    // Method to calculate total price based on the plan
    private BigDecimal calculateTotalPrice(BigDecimal price, Integer quantity, String planName) {
        int days = getDaysFromPlan(planName);
        if (price != null && quantity != null && days > 0) {
            return price.multiply(new BigDecimal(days)).multiply(new BigDecimal(quantity));
        }
        return BigDecimal.ZERO; 
    }

    // method to determine days based on plan name
    private int getDaysFromPlan(String plan) {
        switch (plan.toLowerCase()) {
            case "1 month": return 30;
            case "2 months": return 60;
            case "3 months": return 90;
            case "6 months": return 180;
            default: return 0; 
        }
    }
    
    

}

package com.amstech.dairy.management.system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.dairy.management.system.entity.Address;
import com.amstech.dairy.management.system.entity.City;
import com.amstech.dairy.management.system.entity.Country;
import com.amstech.dairy.management.system.entity.State;
import com.amstech.dairy.management.system.entity.User;
import com.amstech.dairy.management.system.entity.UserRole;
import com.amstech.dairy.management.system.model.request.AddresModelRequest;
import com.amstech.dairy.management.system.repo.AddresRepo;
import com.amstech.dairy.management.system.repo.CityRepo;
import com.amstech.dairy.management.system.repo.CountryRepo;
import com.amstech.dairy.management.system.repo.StateRepo;
import com.amstech.dairy.management.system.repo.UserRepo;


import jakarta.transaction.Transactional;

@Service
public class AddressService {
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private CountryRepo countryRepo;
    
    @Autowired
    private AddresRepo addresRepo;
    
   
//    @Transactional  
//    public void addAdress(AddresModelRequest addresModelRequest) throws Exception {
//        
//        Country country = countryRepo.findByName(addresModelRequest.getCountryName())
//                .orElseGet(() -> countryRepo.save(new Country(addresModelRequest.getCountryName())));
//
//        State state = stateRepo.findByNameAndCountry(addresModelRequest.getStateName(), country)
//                .orElseGet(() -> stateRepo.save(new State(addresModelRequest.getStateName(), country)));
//
//        System.out.println("State ID for city query: " + state.getId());
//
//        City city = cityRepo.findByNameAndState(addresModelRequest.getCityName(), state)
//                .orElseGet(() -> cityRepo.save(new City(addresModelRequest.getCityName(), state)));
//
//        System.out.println("City ID after fetch: " + city.getId());
//
//        User user = userRepo.findById(addresModelRequest.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found")); 
//
//        Address address = new Address();
//        address.setUser(user);
//        address.setCity(city);
//        address.setLendMark(addresModelRequest.getLendMark()); 
//        address.setPostalCode(addresModelRequest.getPostalCode()); 
//        address.setMapLocation(addresModelRequest.getMapLocation()); 
//        address.setNearBy(addresModelRequest.getNearBy()); 
//
//        addresRepo.save(address);
//    }
    @Transactional  
    public Address addAddress(AddresModelRequest addressModelRequest) throws Exception {
        
        Country country = countryRepo.findByName(addressModelRequest.getCountryName())
                .orElseGet(() -> countryRepo.save(new Country(addressModelRequest.getCountryName())));

        State state = stateRepo.findByNameAndCountry(addressModelRequest.getStateName(), country)
                .orElseGet(() -> stateRepo.save(new State(addressModelRequest.getStateName(), country)));

        City city = cityRepo.findByNameAndState(addressModelRequest.getCityName(), state)
                .orElseGet(() -> cityRepo.save(new City(addressModelRequest.getCityName(), state)));

        User user = userRepo.findById(addressModelRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")); 

        Address address = new Address();
        address.setUser(user);
        address.setCity(city);
        address.setLendMark(addressModelRequest.getLendMark());
        address.setPostalCode(addressModelRequest.getPostalCode());
        address.setMapLocation(addressModelRequest.getMapLocation());
        address.setNearBy(addressModelRequest.getNearBy());

        return addresRepo.save(address); // Return the saved Address
    }


}

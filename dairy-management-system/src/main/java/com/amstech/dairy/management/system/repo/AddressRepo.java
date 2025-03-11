package com.amstech.dairy.management.system.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amstech.dairy.management.system.entity.Address;





@Repository
public interface AddressRepo  extends JpaRepository<Address, Integer> {
	 List<Address> findByUserId(int userId);
	
}

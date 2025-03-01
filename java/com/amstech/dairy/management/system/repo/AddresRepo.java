package com.amstech.dairy.management.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.dairy.management.system.entity.Address;

public interface AddresRepo extends JpaRepository<Address, Integer> {
	

}

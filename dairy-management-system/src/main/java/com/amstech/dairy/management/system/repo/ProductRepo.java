package com.amstech.dairy.management.system.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.dairy.management.system.entity.MilkProduct;
import com.amstech.dairy.management.system.entity.Order;
import com.amstech.dairy.management.system.entity.User;

public interface  ProductRepo extends JpaRepository<MilkProduct, Integer> {

	@Query("SELECT p FROM MilkProduct p WHERE LOWER(p.productName) = LOWER(:productName)")
	Optional<MilkProduct> findByProductNameUser(@Param("productName") String productName);
}

package com.amstech.dairy.management.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.dairy.management.system.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}

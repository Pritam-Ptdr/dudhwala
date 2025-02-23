package com.amstech.dairy.management.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.dairy.management.system.entity.MilkProduct;

public interface ProductRepo  extends JpaRepository<MilkProduct, Integer>{

}

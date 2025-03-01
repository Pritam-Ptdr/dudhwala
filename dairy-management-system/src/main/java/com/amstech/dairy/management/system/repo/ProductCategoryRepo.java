package com.amstech.dairy.management.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.dairy.management.system.entity.ProductCategory;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Integer> {

}

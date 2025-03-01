package com.amstech.dairy.management.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amstech.dairy.management.system.entity.ProductCategory;

@Repository
public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Integer> {

}

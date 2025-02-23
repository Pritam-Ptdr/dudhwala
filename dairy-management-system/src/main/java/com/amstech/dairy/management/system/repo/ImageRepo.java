package com.amstech.dairy.management.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amstech.dairy.management.system.entity.Image;

@Repository
public interface  ImageRepo extends JpaRepository<Image, Integer> {

}

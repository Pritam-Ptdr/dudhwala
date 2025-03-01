package com.amstech.dairy.management.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.dairy.management.system.entity.Image;

public interface  ImageRepo extends JpaRepository<Image, Integer> {

}

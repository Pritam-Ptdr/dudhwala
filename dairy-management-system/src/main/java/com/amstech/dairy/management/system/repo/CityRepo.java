package com.amstech.dairy.management.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amstech.dairy.management.system.entity.City;


@Repository
public interface CityRepo  extends JpaRepository<City, Integer> {

}

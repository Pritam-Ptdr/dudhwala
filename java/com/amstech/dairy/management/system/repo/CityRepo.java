package com.amstech.dairy.management.system.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amstech.dairy.management.system.entity.City;
import com.amstech.dairy.management.system.entity.State;


@Repository
public interface CityRepo  extends JpaRepository<City, Integer> {

	Optional<City> findByNameAndState (String name , State state);
}

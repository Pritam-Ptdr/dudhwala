package com.amstech.dairy.management.system.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.dairy.management.system.entity.Country;

public interface CountryRepo  extends JpaRepository<Country, Integer>{

	Optional<Country> findByName(String name);
}

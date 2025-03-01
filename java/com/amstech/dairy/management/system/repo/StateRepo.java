package com.amstech.dairy.management.system.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.dairy.management.system.entity.Country;
import com.amstech.dairy.management.system.entity.State;

public interface StateRepo extends JpaRepository<State,Integer > {

	Optional<State>findByNameAndCountry(String name ,Country country);
}

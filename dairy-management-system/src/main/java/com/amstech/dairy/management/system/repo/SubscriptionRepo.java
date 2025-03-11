package com.amstech.dairy.management.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.amstech.dairy.management.system.entity.Subscription;

public interface SubscriptionRepo  extends JpaRepository<Subscription, Integer>{

	

}

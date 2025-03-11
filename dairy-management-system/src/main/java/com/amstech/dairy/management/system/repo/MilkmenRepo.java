package com.amstech.dairy.management.system.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.dairy.management.system.entity.User;

//import model.Milkman;

public interface MilkmenRepo extends JpaRepository<User, Integer>  {
}
//	@Query("select m from Milkman m where m.status = :status")
//	Milkman findByStatus(@Param("status") String status);
//
//	
//	@Query("select m from Milkman m where m.id = :id")
//	Milkman findById(@Param("id") int id);
//	
//	@Query("select m from Milkman m where m.status = 'Active'")
//	List<Milkman> findActiveMilkmen();
//	
//	@Query("select m from Milkman m where m.rating >= :rating")
//	List<Milkman> findByMinimumRating(@Param("rating") int rating);
//	
//	
//	
//}

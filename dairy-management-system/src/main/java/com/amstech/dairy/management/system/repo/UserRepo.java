package com.amstech.dairy.management.system.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amstech.dairy.management.system.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("select e from User e where e.email=:email")
	User findByEmailUser(@Param("email") String email);
	
	@Query("select e from User e where e.mobileNumber=:mobileNumber")
	User findByMobileNumberUser(@Param("mobileNumber") String mobileNumber);
	
//	
//	
//	 @Query("select e from User e where e.firstName=:firstName") 
//	    List<User> findByFirstNameUsers(@Param("firstName") String firstName);
//	 
//	 @Modifying
//	 @Transactional
//	 @Query("UPDATE User e SET e.firstName = :firstName WHERE e.id = :id")
//	 int updateByUser(@Param("id") Integer id , @Param("firstName") String firstName);
//	 
//	 
//	 @Query("SELECT e FROM User e WHERE (e.mobileNumber = :mobileNumber OR e.email = :email) AND e.password = :password")
//	 User findByMobileUser(@Param("mobileNumber") String mobileNumber, @Param("email") String email, @Param("password") String password);
//
//	
//	 
//	 @Query("SELECT ur.role.id FROM UserRole ur WHERE ur.user.id = :userId")
//	 Optional<Integer> findRoleByUserId(@Param("userId") Integer userId);
//
//
//	
}

package com.amstech.dairy.management.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amstech.dairy.management.system.entity.User;
import com.amstech.dairy.management.system.model.request.UserForgotPasswordModelRequest;
import com.amstech.dairy.management.system.model.request.UserModelRequest;
import com.amstech.dairy.management.system.model.request.UserSignupModelRequest;
import com.amstech.dairy.management.system.model.request.UserUpdateRequestModel;
import com.amstech.dairy.management.system.model.response.ProductResponseModel;
import com.amstech.dairy.management.system.model.response.UserModelResponse;
import com.amstech.dairy.management.system.repo.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public void signup(UserSignupModelRequest userModelRequest) throws Exception {

		com.amstech.dairy.management.system.entity.User userEmail = userRepo
				.findByEmailUser(userModelRequest.getEmail());
		if (userEmail != null) {
			throw new Exception("Email already exists, please try another.");
		}

		User userMobileNumber = userRepo.findByMobileNumberUser(userModelRequest.getMobileNumber());
		if (userMobileNumber != null) {
			throw new Exception("Mobile number already exists, please try another.");
		}

		// Create new user
		User user = new User();
		user.setFirstName(userModelRequest.getFirstName());
		user.setLastName(userModelRequest.getLastName());
		user.setEmail(userModelRequest.getEmail());
		user.setMobileNumber(userModelRequest.getMobileNumber());
		user.setPassword(userModelRequest.getPassword());

		user.setGender(userModelRequest.getGender());
		user.setDateOfBirth(userModelRequest.getDateOfBirth());

		User savedUser = userRepo.save(user);
		System.out.println("UserId saved: " + savedUser.getId());
	}

	public void updateAll(UserUpdateRequestModel userUpdateRequestModel) throws Exception {

		Optional<User> userOptional = userRepo.findById(userUpdateRequestModel.getId());

		if (!userOptional.isPresent()) {
			throw new Exception("User does not exist ");
		}
		User user = userOptional.get();

		if (userUpdateRequestModel.isEmailUpdate()) {
			if (!userUpdateRequestModel.isEmailVarified()) {
				throw new Exception(" Email is not varified , place varified Email......");
			}

			User userByEmail = userRepo.findByEmailUser(userUpdateRequestModel.getEmail());

			if (userByEmail != null) {
				throw new Exception("User email already existing");
			}
		}
		user.setEmail(userUpdateRequestModel.getEmail());

		User userByMobileNumber = userRepo.findByMobileNumberUser(userUpdateRequestModel.getMobileNumber());

		if (userByMobileNumber != null) {
			throw new Exception("User mobileNumber already existing");
		}
		user.setMobileNumber(userUpdateRequestModel.getMobileNumber());

		user.setFirstName(userUpdateRequestModel.getFirstName());
		user.setLastName(userUpdateRequestModel.getLastName());
		user.setDateOfBirth(userUpdateRequestModel.getDateOfBirth());
		user.setGender(userUpdateRequestModel.getGender());

		userRepo.save(user);

	}

	  @Transactional
	    public void forgotPassword(String userName, String newPassword) throws Exception {
	        // Trim to remove any leading or trailing spaces
	        userName = userName.trim();

	        // Fix: Ensure it searches by email or mobile number correctly
	        Optional<User> userOptional = userRepo.findByEmailOrMobileNumber(userName, userName);

	        if (!userOptional.isPresent()) {
	            throw new Exception("User not found with email or mobile number: " + userName);
	        }

	        // Update the password
	        int updateCount = userRepo.forgotPassword(userName, newPassword);

	        if (updateCount == 0) {
	            throw new Exception("Failed to update password");
	        }
	    }
	public void softDeleteById(Integer id , boolean isActive) throws Exception {

		Optional<User> userOptional = userRepo.findById(id);

		if (!userOptional.isPresent()) {
			throw new Exception("User does not exist");
		}
		User user = userOptional.get();

//		if (user.getIs_deleted() == 1) {
//			throw new Exception("User already deleted");
//		}
//
//		user.setIs_deleted(1);
//		userRepo.save(user);
		user.setIs_deleted(isActive ? 0 : 1);
        userRepo.save(user);
	}

	public UserModelResponse UserfindByMobile(String mobileNumber) throws Exception {
		Optional<User> userOptional = Optional.ofNullable(userRepo.findByMobileNumberUser(mobileNumber));

		if (!userOptional.isPresent()) {
			throw new Exception("User does not exist...");
		}

		User user = userOptional.get();

		UserModelResponse responseModel = new UserModelResponse();

		responseModel.setFirstName(user.getFirstName());
		responseModel.setLastName(user.getLastName());
		responseModel.setEmail(user.getEmail());
		responseModel.setMobileNumber(user.getMobileNumber());

		// Ensure correct conversion
		responseModel.setDateOfBirth((java.sql.Date) user.getDateOfBirth());

		responseModel.setGender(user.getGender());

		return responseModel;
	}

	public List<UserModelResponse> findAllUser(Integer page, Integer size) throws Exception {
		
		org.springframework.data.domain.Page<User> userPage = userRepo.findAll(PageRequest.of(page, size)); // Get
																											// Page<User>
		List<User> userList = userPage.getContent(); // Extract List<User>

		List<UserModelResponse> userResponseModel = new ArrayList<>();

		for (User user : userList) {
			UserModelResponse responseModel = new UserModelResponse();
			responseModel.setFirstName(user.getFirstName());
			responseModel.setLastName(user.getLastName());
			responseModel.setEmail(user.getEmail());
			responseModel.setMobileNumber(user.getMobileNumber());
			responseModel.setDateOfBirth((java.sql.Date) user.getDateOfBirth());

			userResponseModel.add(responseModel);
		}
		return userResponseModel;
	}

}
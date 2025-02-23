package com.amstech.dairy.management.system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.amstech.dairy.management.system.entity.User;
import com.amstech.dairy.management.system.model.request.UserModelRequest;
import com.amstech.dairy.management.system.model.request.UserUpdateRequestModel;
import com.amstech.dairy.management.system.model.response.ProductResponseModel;
import com.amstech.dairy.management.system.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public void signup(UserModelRequest userModelRequest) throws Exception {

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

	public void forgotPassword(UserModelRequest userModelRequest) throws Exception {

		String mobileNumber = userModelRequest.getMobileNumber().trim();

		Optional<User> userOptional = Optional.of(userRepo.findByMobileNumberUser(mobileNumber));

		if (!userOptional.isPresent()) {
			throw new Exception("User not found with mobile number " + mobileNumber);
		}

		User user = userOptional.get();

		int userUpdate = userRepo.forgotPassword(user.getEmail(), user.getMobileNumber(),
				userModelRequest.getPassword());

		if (userUpdate > 0) {
			System.out.println("User Password update Successfuly");
		} else {
			System.out.println("User Password update failed");
		}
	}

	public void softDeleteById(Integer id) throws Exception {

		Optional<User> userOptional = userRepo.findById(id);

		if (!userOptional.isPresent()) {
			throw new Exception("User does not exist");
		}
		User user = userOptional.get();

		if (user.getIs_deleted() == 1) {
			throw new Exception("User already deleted");
		}

		user.setIs_deleted(1);
		userRepo.save(user);
	}

	

}
package com.amstech.dairy.management.system.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.data.DataRestResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.amstech.dairy.management.system.model.request.UserForgotPasswordModelRequest;
import com.amstech.dairy.management.system.model.request.UserLoginModelRequest;
import com.amstech.dairy.management.system.model.request.UserModelRequest;
import com.amstech.dairy.management.system.model.request.UserSignupModelRequest;
import com.amstech.dairy.management.system.model.request.UserUpdateRequestModel;
import com.amstech.dairy.management.system.model.response.UserModelResponse;
import com.amstech.dairy.management.system.responce.RestResponce;
import com.amstech.dairy.management.system.service.UserService;

@Controller
@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserService userService;

	@Autowired
	public UserController() {
		System.out.println("UserControoler : object created");
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Map<String, Object>> signup(@RequestBody UserSignupModelRequest userModelRequest) {
		LOGGER.info("SAVING USER DATA WITH EMAIL: {}", userModelRequest.getEmail());

		try {
			userService.signup(userModelRequest);
			return ResponseEntity.ok(RestResponce.build().data(userModelRequest).success(200)
					.message("User Created Successfully").getResponse());
		} catch (Exception e) {
			LOGGER.error("Error during signup {}", e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(RestResponce.build().error(404).message("User signup failed").getResponse());
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/UserUpdate", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Map<String, Object>> UserUpdate(@RequestBody UserUpdateRequestModel userUpdateRequestModel) {

		LOGGER.info("USER DATA UPDATE {}", userUpdateRequestModel.getId());

		try {
			userService.updateAll(userUpdateRequestModel);
			return ResponseEntity.ok(RestResponce.build().data(userUpdateRequestModel.getFirstName()).success(200)
					.message("User Update Created Successfully").getResponse());
		} catch (Exception e) {
			LOGGER.error("Error during UserUpdate {}", e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(RestResponce.build().error(404).message("User signup failed").getResponse());
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/forgotUserPassword", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Map<String, Object>> forgotUserPassword(@RequestBody UserForgotPasswordModelRequest userForgotPasswordModelRequest) {
        try {
            userService.forgotPassword(userForgotPasswordModelRequest.getUserName(), userForgotPasswordModelRequest.getPassword());

            return ResponseEntity.ok(RestResponce.build()
                    .success(200)
                    .message("User password updated successfully")
                    .getResponse());
        } catch (Exception e) {
            LOGGER.error("Error updating password: {}", e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(RestResponce.build()
                            .error(500)
                            .message("User password update failed: " + e.getMessage())
                            .getResponse());
        }
    }

	@RequestMapping(method = RequestMethod.DELETE, value = "softDeleteby", produces = "application/json")
	public ResponseEntity<Map<String, Object>> softDeleteById(@RequestParam("id") Integer id , @RequestParam(required = false) Integer status) {

		try {
			boolean isActive = (status == null || status == 0);
			userService.softDeleteById(id ,isActive);
			return ResponseEntity
					.ok(RestResponce.build().data(id).success(200).message("User Status updateting  Successfully").getResponse());
		} catch (Exception e) {
			LOGGER.error("Error User Status Deleteing updateting  faild {}", e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(RestResponce.build().error(404).message("Error User Status Deleteing updateting  faild").getResponse());
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/UserfindByMobile", produces = "application/json")
	public ResponseEntity<Map<String, Object>> UserfindByMobile(@RequestParam("mobileNumber") String mobileNumber) {

		LOGGER.info("user is find by mobileNumber {}", mobileNumber);
		try {
			UserModelResponse userResponseModel = userService.UserfindByMobile(mobileNumber);
			return ResponseEntity
					.ok(RestResponce.build().data(mobileNumber).success(200).message(" Successfully").getResponse());
		} catch (Exception e) {
			LOGGER.error("Error during signup {}", e.getMessage(), e);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(RestResponce.build().error(404).message("User find By Mobile Number failed").getResponse());
		}
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/findAllUser", produces = "application/json")
	public ResponseEntity<Object> findAllUser(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

		
		LOGGER.info("User is find all data by id ");

		try {

			List<UserModelResponse> userResponseModel = userService.findAllUser(page, size);
			return new ResponseEntity<>(userResponseModel, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(" error fetching User all data " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
//	@RequestMapping(method = RequestMethod.GET, value = "/findAllUser", produces = "application/json")
//	public ResponseEntity<Object> findAllUser(@RequestParam(value = "page", defaultValue = "-1") Integer page,
//			@RequestParam(value = "size", defaultValue = "-1") Integer size) {
//
//		LOGGER.info("User is finding all data");
//
//		try {
//			List<UserModelResponse> userResponseModel = userService.findAllUser(page, size);
//			return new ResponseEntity<>(userResponseModel, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>("Error fetching user data: " + e.getMessage(),
//					HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> login(@RequestBody UserLoginModelRequest userRequestModel) {

		System.out.print("User login in website :" + userRequestModel);

		try {
			String result = userService.login(userRequestModel);

			if (result.equals("MANAGER_DASHBOARD")) {
				return ResponseEntity.ok("Redirect to Manager Dashboard");
			} else if (result.equals("EMPLOYEE_DASHBOARD")) {
				return ResponseEntity.ok("Redirect to Employee Dashboard");
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
			}
		} catch (Exception e) {
			return new ResponseEntity<>("User Login not successful: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}

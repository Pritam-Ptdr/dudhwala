package com.amstech.dairy.management.system.controller;

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

import com.amstech.dairy.management.system.model.request.UserModelRequest;
import com.amstech.dairy.management.system.model.request.UserUpdateRequestModel;
import com.amstech.dairy.management.system.service.UserService;

@Controller
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserService userService;

	@Autowired
	public UserController(){
		System.out.println("UserControoler : object created");
        this.userService= userService;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> signup(@RequestBody UserModelRequest userModelRequest) {

		System.out.print("saving user data with email : :" + userModelRequest);

		try {
			userService.signup(userModelRequest);
			return new ResponseEntity<Object>("User created successfully " + userModelRequest, HttpStatus.OK);

		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<Object>("Invalid User signup request . Place cheak the input fields." + userModelRequest + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(method= RequestMethod.PUT , value = "/UserUpdate" ,consumes = "application/json" , produces = "application/json")
	public ResponseEntity<Object> UserUpdate(@RequestBody UserUpdateRequestModel userUpdateRequestModel){
		
		System.out.println("USer data update ");
		
		try {
			userService.updateAll(userUpdateRequestModel);
			return new ResponseEntity<Object>("User data update successfuly " + userUpdateRequestModel.getId() , HttpStatus.OK);
			
		}catch(Exception e) {
			
			return new ResponseEntity<Object>(" Error : User data note update " + e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
					
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT , value = "/updatePassword" , consumes = "application/json" ,  produces = "application/json" )
	public ResponseEntity<Object>updatePassword(@RequestBody UserModelRequest userModelRequest){
		
		System.out.println("User Passwor Update");
		
		try {
			userService.forgotPassword(userModelRequest);
			return new ResponseEntity<Object>("User Password update successfuly" +userModelRequest.getFirstName() , HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("User Password note update " +e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "softDeleteby", produces = "application/json")
	public ResponseEntity<Object> softDeleteById(@RequestParam("id") Integer id){
		
		try {
			userService.softDeleteById(id);
			return new ResponseEntity<Object>("User delete Successfully" , HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("faild user deleted" , HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}


	
}

package com.blog.code.controllers;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.code.payloads.ApiResponse;
import com.blog.code.payloads.UserDto;
import com.blog.code.services.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//POST -Create User
	
	
	@PostMapping("/CreateNewUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto= this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	
	//Put - Update User
	@PutMapping("/userUpdate/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId")Integer userId)
	{
		UserDto updateUserDto = this.userService.updateUser(userDto, userId);
		return  ResponseEntity.ok(updateUserDto);
		
	}
	
	//Delete - Delete User
	@DeleteMapping("/userDelete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId")Integer userId)
	{
		 this.userService.deleteUser(userId);
		 return new ResponseEntity<Object>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
	}
	//Get - All User Get
	
	@GetMapping("/getAllDetails")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//Get - User Get
	@GetMapping("/getUserDetail/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(this.userService.getUserById(userId));
		
	}
	
	

}

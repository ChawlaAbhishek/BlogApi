package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.UserDto;
import com.blog.services.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<UserDto> saveUserHandler(@Valid @RequestBody UserDto userDto) {
		
		UserDto createdUserDto =  userService.createUser(userDto);
		
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<UserDto> updateUserHandler(@Valid @RequestBody UserDto userDto ,@PathVariable("userId") Integer userId){
		UserDto updatedUserDto = userService.updateUser(userDto, userId);
		
		return new ResponseEntity<UserDto>(updatedUserDto,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("users/{userId}")
	public void deleteUserHandler(@PathVariable("userId") Integer userId) {
		userService.deleteUser(userId);
	}
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsersHandler(){
		
		List<UserDto> userDtos= userService.getAllUsers();
		
		return new ResponseEntity<>(userDtos,HttpStatus.OK);
	}
	@GetMapping("/users/{userId}")
	public ResponseEntity<?> getUserByIdHandler(@PathVariable("userId") Integer userId){
		
		UserDto userDto = userService.getUserById(userId);
		
		return new ResponseEntity<>(userDto,HttpStatus.OK);
		
	}

}

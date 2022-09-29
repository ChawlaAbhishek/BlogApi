package com.blog.services;

import java.util.List;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,Integer userId) throws ResourceNotFoundException;
	UserDto getUserById(Integer userId) throws ResourceNotFoundException;
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId) throws ResourceNotFoundException;

}

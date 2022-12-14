package com.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserDao;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
//	    UserServiceImpl userServiceImpl = new UserServiceImpl();
//	    User user = userServiceImpl.dtoToUser(userDto);
//	   User savedUser =  userDao.save(user);
//	    
//		return userServiceImpl.UserToDto(savedUser);
		
		User user = this.dtoToUser(userDto);
		
		User savedUser = userDao.save(user);
		
		return this.UserToDto(savedUser);
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("user does not exist with"+userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = userDao.save(user);
		
		return this.UserToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User does not exist with"+userId));
		return this.UserToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = userDao.findAll();
		
		List<UserDto> userDtos = users.stream().map(user->this.UserToDto(user)).collect(Collectors.toList());
		
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User do not exist with"+userId));
		userDao.delete(user);
		
	}
	
	public User dtoToUser(UserDto userDto) {
		
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		User user = modelMapper.map(userDto, User.class);
		
		return user;
		
	}
	
	public UserDto UserToDto(User user) {
//		UserDto userDto = new UserDto();
//		
//		userDto.setName(user.getName());
//		userDto.setId(user.getId());
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
		
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
		return userDto;
	}

}

package com.andre.service;

import org.springframework.stereotype.Service;

import com.andre.model.User;
import com.andre.model.UserDTO;

@Service
public class UserService {
	public User userDtoToUser(UserDTO  userDto) {
		return new User(userDto.getUsername(), userDto.getPwd(), 
				userDto.getFirstName(), userDto.getLastName(),
				userDto.getMiddleName(), userDto.getPhone());
	}
}

package com.andre.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andre.model.User;

@Repository
public interface IUser extends JpaRepository<User, Integer>{
	public User findByUsername(String username);
	public User findByEmailId(String emailId);
}

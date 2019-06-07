package com.example.demo.service.base;

import java.util.List;

import com.example.demo.model.User;

public interface UserService  {
	List<User> findAll();
	User addUser(User u);
	User deleteUser(long id);
	User findById(long id);
	User updateUser(User u);
	User findByUserName(String username);
//	User findPassword(String password);
	
	
}

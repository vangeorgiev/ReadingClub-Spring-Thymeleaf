package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.EntityAlreadyExistException;
import com.example.demo.exceptions.EntityDoesntExistsException;
import com.example.demo.exceptions.InvalidRequestException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.base.UserService;
import com.example.demo.validatePatterns.PatternValidator;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository repo;

	@Autowired
	public UserServiceImpl(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<User> findAll() {
		return repo.findAll();
	}

	
	@Override
	public User addUser(User u) {
		User user = repo.findByUserName(u.getUserName());

		if (user != null) {
			throw new EntityAlreadyExistException("User with same username already exist");
		}

		user  = repo.findByEmail(u.getEmail());

		if (user != null) {
			throw new EntityAlreadyExistException("User with same email already exist");
		}
		return repo.save(u);
	}

	@Override
	public User deleteUser(long id) {
		Optional<User> u = repo.findById(id);
		 repo.deleteById(id);
		 return u.orElseThrow(() -> new EntityDoesntExistsException("cannot find entity with such id"));
	}

	@Override
	public User findById(long id) {
		return repo.findById(id).orElseThrow(() -> new EntityDoesntExistsException("cannot find entity with such id"));
	}
 
	@Override
	public User updateUser(User u) {
		
		User user = findById(u.getId());
		
		if(user.getFirstName()== null && user.getFirstName().isEmpty()) {
			throw new InvalidRequestException("You must set first name");
		}
		
		if(!user.getUserName().equals(u.getUserName())) {
			throw new InvalidRequestException("Cannot change user's username");
		}
			
		if(!user.getEmail().equals(u.getEmail())) {
			throw new InvalidRequestException("Cannot change user's email");
		}
		
		u.setModified(LocalDateTime.now());
		return repo.save(u);
	}

	@Override
	public User findByUserName(String username) {
		User u = this.repo.findByUserName(username);
		
		if(u == null ) {
		throw new EntityDoesntExistsException("user doesnt exist");
		}
	
		
		return u;
		
	}

//	@Override
//	public User findPassword(String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	
	

}

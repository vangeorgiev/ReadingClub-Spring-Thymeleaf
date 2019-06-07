package com.example.demo.controller;

import java.net.URI;
import java.util.InputMismatchException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exceptions.InvalidRequestException;
import com.example.demo.model.User;
import com.example.demo.model.ENUMS.Role;
import com.example.demo.service.base.UserService;
import com.example.demo.validatePatterns.PatternValidator;


@Controller
@RequestMapping("/users")

public class UserController {

	private UserService service;

	@Autowired
	public UserController(UserService us) {
		this.service = us;
	} 
	
	@GetMapping 
	public List<User> findAll() {
		return service.findAll();
		}

	//get users  MAIN PAGE
	@GetMapping("/list")
	public String listUsers(Model model) {
		
		List<User> theUsers = service.findAll();
		 
		model.addAttribute("users", theUsers);
		
		return "users/listUsers"; 
	}
	

	
	//
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		User theUser = new User();
		
		theModel.addAttribute("users", theUser);
		
		return "users/userFile"; 
	}
	
	//TODO
	//save user
	@PostMapping("/save")
	public String saveUser( @ModelAttribute("users") User u) {
		
		if(u.getEmail().matches(PatternValidator.email)	) {
			service.updateUser(u);
			return "redirect:/users/list";
		}
		// use a redirect to prevent duplicate submissions
		
		return "redirect:/users/showFormForAdd";
	}
	
	//register method button
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("users") User u) {
		
		if(u.getEmail().matches(PatternValidator.email)	) {
			service.addUser(u);
			return "redirect:/";
		}
		// use a redirect to prevent duplicate submissions
		
		return "redirect:/users/register";
	}
		
		
	
	@GetMapping("/{id}")
	public User findById(@PathVariable long id) {
		return service.findById(id);
	}
  
	@PostMapping
		ResponseEntity<User> addUser(@RequestBody User user ) {
		User createdUser = service.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().
				pathSegment("{id}").buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(location).body(createdUser);
	}

	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable long id) {
		User u = findById(id);
		
		if(id != u.getId()) {
			throw new InvalidRequestException("Ids in path and body are different");
		}
		return service.deleteUser(id);
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("userId") long theId) {
		
		// delete the employee
		service.deleteUser(theId);
		
		// redirect to /employees/list
		return "redirect:/users/list";
		
	}
	
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User u, @PathVariable long id)  {
		if (id != u.getId()) {
			throw new InvalidRequestException("Ids in path and body are different");
		}

		return service.updateUser(u);
	}
	

	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") long theId,
									Model theModel) {
		// get the employee from the service
		User user = service.findById(theId);
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("users", user);
		
		// send over to our form
		return "users/userFile";			
	}
	
	@GetMapping("/register")
	public String registerUser(Model model) {
		
		return "users/registerUser"; 
	}
	
	
	//LOGIN BUTTON
	@GetMapping("/login")
	public String loginUser(Model model) {
		return "users/loginUser";
	}
	
	//Login validation
	@PostMapping("/loginUserPost")
	public String loginUserPost(Model model, @ModelAttribute("users") User user,
			@RequestParam("password") String password, HttpSession session) {
		
		//TODO: Check in the Database user/password
		
		User u = service.findByUserName(user.getUserName());
		
		if((u.getPassword().equals(user.getPassword()) && user.getPassword().equals(password))) {
			
		}
		session.setAttribute("usedId", user.getId());
		session.setAttribute("role", u.getRole());
		
		return "redirect:/users/list";
	}
	
	
	
	
	//HOLD ID
	@GetMapping("/holdId")
	public String holdId(@RequestParam("userId") long theId,
			Model theModel) {
		// get the employee from the service


//TODO
	return "comments";			
	}
	
	@GetMapping("/logout")
	public String logOut() {
		return "index";
	}
}

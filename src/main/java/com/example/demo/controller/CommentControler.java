package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exceptions.InvalidRequestException;
import com.example.demo.model.Comment;
import com.example.demo.model.Publication;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.base.CommentService;
import com.example.demo.service.base.UserService;


@Controller
@RequestMapping("/comments")
public class CommentControler {

	private UserService userService;
	private CommentService service;
	
	public CommentControler(CommentService cs) {
		this.service = cs;
	}
	
	//get users  Comment PAGE
	@GetMapping("/list")
	public String listUsersComment(Model model) {
		
		
		
		return "comments"; 
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping
	public List<Comment> findAll() {

		return service.findAll();
	}

	@GetMapping("/{id}")
	public Comment findById(@PathVariable long id) {
		return service.findById(id);
	}
  
	@PostMapping
		ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
		Comment createComment = service.addComment(comment);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().
				pathSegment("{id}").buildAndExpand(createComment.getId()).toUri();
		return ResponseEntity.created(location).body(createComment);
	}

	@DeleteMapping("/{id}")
	public Comment deleteComment(@PathVariable long id) {
		Comment c = findById(id);
		
		if(id != c.getId()) {
			throw new InvalidRequestException("Ids in path and body are different");
		}
		return service.deleteComment(id);
	}

	@PutMapping("/{id}")
	public Comment updateComment(@RequestBody Comment c, @PathVariable long id)  {
		if (id != c.getId()) {
			throw new InvalidRequestException("Ids in path and body are different");
		}

		return service.updateComment(c);
	}

}

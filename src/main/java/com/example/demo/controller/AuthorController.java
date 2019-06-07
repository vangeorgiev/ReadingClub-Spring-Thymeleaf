package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exceptions.InvalidRequestException;
import com.example.demo.model.Author;
import com.example.demo.service.base.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	private AuthorService service;

	@Autowired
	public AuthorController(AuthorService as) {
		this.service = as;
	}

	@GetMapping
	public List<Author> findAll() {

		return service.findAll();
	}

	@GetMapping("/{id}")
	public Author findById(@PathVariable long id) {
		return service.findById(id);
	}
  
	@PostMapping
		ResponseEntity<Author> addAuthor(@RequestBody Author author ) {
		Author createdAuthor = service.addAuthor(author);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().
				pathSegment("{id}").buildAndExpand(createdAuthor.getId()).toUri();
		return ResponseEntity.created(location).body(createdAuthor);
	}

	@DeleteMapping("/{id}")
	public Author deleteAuthor(@PathVariable long id) {
		Author a = findById(id);
		
		if(id != a.getId()) {
			throw new InvalidRequestException("Ids in path and body are different");
		}
		return service.deleteAuthor(id);
	}

	@PutMapping("/{id}")
	public Author updateAuthor(@RequestBody Author a, @PathVariable long id)  {
		if (id != a.getId()) {
			throw new InvalidRequestException("Ids in path and body are different");
		}

		return service.updateAuthor(a);
	}
}

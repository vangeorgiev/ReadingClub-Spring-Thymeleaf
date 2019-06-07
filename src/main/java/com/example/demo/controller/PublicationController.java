package com.example.demo.controller;

import java.net.URI;
import java.util.List;

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
import com.example.demo.model.Comment;
import com.example.demo.model.Publication;
import com.example.demo.service.base.PublicationService;

@RestController
@RequestMapping("/publications")
public class PublicationController {

	PublicationService service;
	
	public PublicationController(PublicationService pc) {
		this.service = pc;
	}
	

	@GetMapping
	public List<Publication> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Publication findById(@PathVariable long id) {
		return service.findById(id);
	}
  
	@PostMapping
		ResponseEntity<Publication> addPublication(@RequestBody Publication publication) {
		Publication createPublication = service.addPublication(publication);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().
				pathSegment("{id}").buildAndExpand(createPublication.getId()).toUri();
		return ResponseEntity.created(location).body(createPublication);
	}

	@DeleteMapping("/{id}")
	public Publication deletePublication(@PathVariable long id) {
		Publication p = findById(id);
		
		if(id != p.getId()) {
			throw new InvalidRequestException("Ids in path and body are different");
		}
		return service.deletePublication(id);
	}

	@PutMapping("/{id}")
	public Publication updatePublication(@RequestBody Publication p, @PathVariable long id)  {
		if (id != p.getId()) {
			throw new InvalidRequestException("Ids in path and body are different");
		}

		return service.updatePublication(p);
	}

}

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
import com.example.demo.model.Section;
import com.example.demo.service.base.SectionService;

@RestController
@RequestMapping("/section")
public class SectionController {

	private SectionService service;

	@Autowired
	public SectionController(SectionService ss) {
		this.service = ss;
	}

	@GetMapping
	public List<Section> findAll() {

		return service.findAll();
	}

	@GetMapping("/{id}")
	public Section findById(@PathVariable long id) {
		return service.findById(id);
	}
  
	@PostMapping
		ResponseEntity<Section> addSection(@RequestBody Section section ) {
		Section createdSection = service.addSection(section);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().
				pathSegment("{id}").buildAndExpand(createdSection.getId()).toUri();
		return ResponseEntity.created(location).body(createdSection);
	}

	@DeleteMapping("/{id}")
	public Section deleteSection(@PathVariable long id) {
		Section s = findById(id);
		
		if(id != s.getId()) {
			throw new InvalidRequestException("Ids in path and body are different");
		}
		return service.deleteSection(id);
	}

	@PutMapping("/{id}")
	public Section updateSection(@RequestBody Section s, @PathVariable long id)  {
		if (id != s.getId()) {
			throw new InvalidRequestException("Ids in path and body are different");
		}

		return service.updateSection(s);
	}

}

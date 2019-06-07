package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.EntityDoesntExistsException;
import com.example.demo.model.Publication;
import com.example.demo.model.User;
import com.example.demo.repository.PublicationRepository;
import com.example.demo.service.base.PublicationService;



@Service
public class PublicationServiceImpl implements PublicationService{

	PublicationRepository repo;
	
	@Autowired
	public PublicationServiceImpl(PublicationRepository pr) {
		this.repo = pr;
	}

	@Override
	public List<Publication> findAll() {
		return repo.findAll();
	}

	@Override
	public Publication addPublication(Publication u) {
		return repo.save(u);
	}

	@Override
	public Publication deletePublication(long id) {
		Optional<Publication> p = repo.findById(id);
		 repo.deleteById(id);
		 return p.orElseThrow(() -> new EntityDoesntExistsException("cannot find entity with such id"));
	}

	@Override
	public Publication findById(long id) {
		return repo.findById(id).orElseThrow(() -> new EntityDoesntExistsException("cannot find entity with such id"));
	}

	@Override
	public Publication updatePublication(Publication u) {
		Publication publication = findById(u.getId());
		u.setModified(LocalDateTime.now());
		return repo.save(u);
	}

}

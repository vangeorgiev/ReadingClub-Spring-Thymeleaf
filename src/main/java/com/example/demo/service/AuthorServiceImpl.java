package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.EntityDoesntExistsException;
import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.base.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	AuthorRepository repo;
	
	@Autowired
	public AuthorServiceImpl(AuthorRepository ar) {
		this.repo = ar;
	}
	
	@Override
	public List<Author> findAll() {
		return repo.findAll();
	}

	@Override
	public Author findById(long id) {
		return repo.findById(id).orElseThrow(() -> new EntityDoesntExistsException("cannot find author with such id"));
	}

	@Override
	public Author updateAuthor(Author a) {
		a.setModified(LocalDateTime.now());
		return repo.save(a);
	}

	@Override
	public Author deleteAuthor(long id) {
		Optional<Author> a = repo.findById(id);
		repo.deleteById(id);
		return a.orElseThrow(() -> new EntityDoesntExistsException("cannot find author with such id"));
	}

	@Override
	public Author addAuthor(Author a) {
		return repo.save(a);
	}

}

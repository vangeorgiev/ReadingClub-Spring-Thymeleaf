package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.EntityDoesntExistsException;
import com.example.demo.model.Section;
import com.example.demo.repository.SectionRepository;
import com.example.demo.service.base.SectionService;

@Service
public class SectionServiceImpl implements SectionService {

	private SectionRepository repo;

	@Autowired
	public SectionServiceImpl(SectionRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<Section> findAll() {

		return repo.findAll();
	}

	@Override
	public Section addSection(Section s) {
		return repo.save(s);
	}

	@Override
	public Section deleteSection(long id) {
		Optional<Section> s = repo.findById(id);
		 repo.deleteById(id);
		 return s.orElseThrow(() -> new EntityDoesntExistsException("cannot find entity with such id"));
	}

	@Override
	public Section findById(long id) {
		return repo.findById(id).orElseThrow(() -> new EntityDoesntExistsException("cannot find entity with such id"));
	}

	@Override
	public Section updateSection(Section s) {
		
		s.setModified(LocalDateTime.now());
		return repo.save(s);
	}
}

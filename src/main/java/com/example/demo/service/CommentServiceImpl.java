package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.EntityDoesntExistsException;
import com.example.demo.model.Comment;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.base.CommentService;
import com.mysql.cj.Session;
import com.zaxxer.hikari.util.ClockSource.Factory;


@Service
public class CommentServiceImpl implements CommentService {

	
	private CommentRepository repo;
	
	@Autowired
	public CommentServiceImpl(CommentRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<Comment> findAll() {
		return repo.findAll();
	}

	@Override
	public Comment findById(long id) {
		return repo.findById(id).orElseThrow(() -> new EntityDoesntExistsException("cannot find comment with such id"));
	}

	@Override
	public Comment updateComment(Comment c) {
		c.setModified(LocalDateTime.now());
		return repo.save(c);
	}

	@Override
	public Comment deleteComment(long id) {
		Optional<Comment> c = repo.findById(id);
		repo.deleteById(id);
		return c.orElseThrow(() -> new EntityDoesntExistsException("cannot find comment with such id"));
	}

	@Override
	public Comment addComment(Comment c) {
//		zapazvame sesiqta, vzimame id-to na usera i dobavq komentara s metoda
//		user.add

		return repo.save(c);
	}



}

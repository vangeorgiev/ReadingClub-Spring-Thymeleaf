package com.example.demo.service.base;

import java.util.List;

import com.example.demo.model.Author;

public interface AuthorService {
	List<Author> findAll();
	Author findById(long id);
	Author updateAuthor(Author c);
	Author deleteAuthor(long id);
	Author addAuthor(Author c);
}
 
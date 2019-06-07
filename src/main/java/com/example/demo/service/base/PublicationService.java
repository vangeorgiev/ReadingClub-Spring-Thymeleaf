package com.example.demo.service.base;

import java.util.List;

import com.example.demo.model.Publication;
import com.example.demo.model.User;

public interface PublicationService {

	List<Publication> findAll();
	Publication addPublication(Publication u);
	Publication deletePublication(long id);
	Publication findById(long id);
	Publication updatePublication(Publication u);
}

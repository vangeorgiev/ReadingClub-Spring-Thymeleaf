package com.example.demo.service.base;

import java.util.List;

import com.example.demo.model.Section;

public interface SectionService {
	List<Section> findAll();
	Section addSection(Section s);
	Section deleteSection(long id);
	Section findById(long id);
	Section updateSection(Section s);
}

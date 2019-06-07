package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Section;

@Repository
public interface SectionRepository  extends JpaRepository<Section, Long>{

}

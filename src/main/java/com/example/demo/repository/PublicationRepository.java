package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

}

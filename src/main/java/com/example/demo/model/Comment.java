package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column 
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="author_id", referencedColumnName = "id")
	private User author; 

	@ManyToOne(fetch = FetchType.LAZY)
	private Publication publication;
 
//	private Section section;
//	// fragment
//	// page 

	@Column
	private String text; // MarkDown

	@Column
	private String tags;

	@Column
	private LocalDateTime createdDate = LocalDateTime.now();;

	@Column
	private LocalDateTime modified;

	public Comment() {
	}

	
public Comment(String text, LocalDateTime createdDate, LocalDateTime modified) {
		this.text = text;
		this.createdDate = createdDate;
		this.modified = modified;
	}



//	public Publication getPublication() {
//		return publication;
//	}
//
//
//	public void setPublication(Publication publication) {
//		this.publication = publication;
//	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
}

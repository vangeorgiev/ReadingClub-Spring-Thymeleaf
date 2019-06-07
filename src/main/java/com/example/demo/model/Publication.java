package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Publication  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	@Column
	private String title;
	 
//	@ManyToMany(targetEntity = Author.class)
//	private List<Author> authors;
	
	@Column 
	private String description;
	 
	@Column
	private String tags;
	
//	private Type type;
	// url option
	
	
//	private List<Section> sectionList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "publication")
	private List<Comment> comments;
	
	@Column
	private LocalDateTime createdDate;
	
	@Column
	private LocalDateTime modified;

	public Publication() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

//	public List<Comment> getCommentList() {
//		return comments;
//	}
//
//	public void setCommentList(List<Comment> commentList) {
//		this.comments = commentList;
//	}

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

	

	
}

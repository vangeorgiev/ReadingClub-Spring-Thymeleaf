package com.example.demo.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.model.ENUMS.Gender;
import com.example.demo.model.ENUMS.Role;
import com.example.demo.model.ENUMS.Status;
import com.example.demo.validatePatterns.PatternValidator;

import io.micrometer.core.lang.NonNull;



@Table(name = "user")
@Entity
public class User {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;// AUTO_GENERATED

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	@Pattern(regexp = PatternValidator.email)
	private String email;

	@Column
	private String userName;

	@Column
	private String password;

	@Column
	@Enumerated(EnumType.STRING)
	private Gender gender; 

	@Column
	@Enumerated(EnumType.STRING)
	private Role role; 

	@Column
	@Pattern(regexp = PatternValidator.url)
	private String pictureURL;

	@Column
	@Enumerated(EnumType.STRING)
	private Status status;
	
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Comment> comments;

	@Column
	private LocalDateTime createdDate = LocalDateTime.now();
 
	@Column
	private LocalDateTime modified;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
	
	public void add(Comment tempComment) {
		if(comments == null) {
			comments = new ArrayList<>();
		}
		
		comments.add(tempComment);
		tempComment.setAuthor(this);
	}

}

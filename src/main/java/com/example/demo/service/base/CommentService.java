package com.example.demo.service.base;

import java.util.List;

import com.example.demo.model.Comment;
import com.example.demo.model.User;

public interface CommentService {
	List<Comment> findAll();
	Comment findById(long id);
	Comment updateComment(Comment c);
	Comment deleteComment(long id);
	Comment addComment(Comment c);
	
	
}

package com.Blog.Mangement.BlogMangementSystem.service;

import com.Blog.Mangement.BlogMangementSystem.entity.Comment;
import java.util.List;
public interface CommentService {

	Comment save(Comment theComment);

	Comment findById(Long commentId);

	List<Comment> findAll();

	void deleteById(Long commentId);

}

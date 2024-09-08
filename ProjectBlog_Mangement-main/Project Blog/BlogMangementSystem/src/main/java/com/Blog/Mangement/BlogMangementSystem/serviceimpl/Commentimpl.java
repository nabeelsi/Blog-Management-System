package com.Blog.Mangement.BlogMangementSystem.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Mangement.BlogMangementSystem.DAO.CommentRepostiory;
import com.Blog.Mangement.BlogMangementSystem.entity.Comment;
//import com.Blog.Mangement.BlogMangementSystem.service.CommentService;
import com.Blog.Mangement.BlogMangementSystem.service.CommentService;
@Service
public class Commentimpl implements CommentService {
	@Autowired
	private CommentRepostiory comRep;
	
	@Override
	public Comment save(Comment theComment) {
		// TODO Auto-generated method stub
		return comRep.save(theComment);
	}

	@Override
	public Comment findById(Long commentId) {

		return comRep.findById(commentId).orElseThrow
				(()-> new RuntimeException
		("COMMENT not found with ID: "+commentId)); 
	}

	@Override
	public void deleteById(Long commentId) {
		// TODO Auto-generated method stub
		comRep.deleteById(commentId);
	}
	@Override
	public java.util.List<Comment> findAll(){
		return comRep.findAll();
	}
}

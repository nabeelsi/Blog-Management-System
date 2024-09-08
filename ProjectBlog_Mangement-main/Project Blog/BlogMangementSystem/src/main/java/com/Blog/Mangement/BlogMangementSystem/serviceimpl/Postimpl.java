package com.Blog.Mangement.BlogMangementSystem.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Mangement.BlogMangementSystem.DAO.PostRepostiory;
import com.Blog.Mangement.BlogMangementSystem.entity.Post;
//import com.Blog.Mangement.BlogMangementSystem.entity.Post;
import com.Blog.Mangement.BlogMangementSystem.service.PostService;

@Service
public class Postimpl implements PostService {
	
	@Autowired
	private PostRepostiory thePostRep;



	@Override
	public Post save(Post thePost) {
		// TODO Auto-generated method stub
		return thePostRep.save(thePost);
	}

	@Override
	public Post findByID(Long postId) {

		return thePostRep.findById(postId).orElseThrow
				(()-> new RuntimeException
		("post not found with ID: "+postId)); 
	}

	@Override
	public void deleteById(Long postId) {
		thePostRep.deleteById(postId);

	}

	@Override
	public java.util.List<Post> findAll() {
		return thePostRep.findAll();
	}

}

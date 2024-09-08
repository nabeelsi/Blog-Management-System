package com.Blog.Mangement.BlogMangementSystem.service;

import com.Blog.Mangement.BlogMangementSystem.entity.Post;

public interface PostService {

	Post save(Post thePost);
	Post findByID(Long postId);
	java.util.List<Post> findAll();
	void deleteById(Long postId);
}

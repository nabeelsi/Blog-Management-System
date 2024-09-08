package com.Blog.Mangement.BlogMangementSystem.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Mangement.BlogMangementSystem.entity.Post;

public interface PostRepostiory extends JpaRepository<Post,Long>{

}

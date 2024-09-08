package com.Blog.Mangement.BlogMangementSystem.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Mangement.BlogMangementSystem.entity.Comment;

public interface CommentRepostiory  extends JpaRepository<Comment,Long>{

}

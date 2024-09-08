package com.Blog.Mangement.BlogMangementSystem.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Mangement.BlogMangementSystem.entity.Author;

public interface AuthorRepository extends JpaRepository<Author,Long>{

}

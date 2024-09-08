package com.Blog.Mangement.BlogMangementSystem.service;

import com.Blog.Mangement.BlogMangementSystem.entity.Author;
//import java.Uti
public interface AuthorService {
	
 java.util.List<Author> findAll();
  Author findbyId(Long authorId);
Author save(Author theAuthor);
void deleteById(Long authorId);
//void delete(Long authorId);

}

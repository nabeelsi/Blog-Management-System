package com.Blog.Mangement.BlogMangementSystem.service;

import com.Blog.Mangement.BlogMangementSystem.entity.Category;

public interface CategoryService {

	Category save(Category theCategory);

	java.util.List<Category> findAll();


	Category findByID(Long categoryId);

	void deleteById(Long categoryId);

}

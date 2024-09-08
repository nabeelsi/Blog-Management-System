package com.Blog.Mangement.BlogMangementSystem.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Mangement.BlogMangementSystem.DAO.CategoryRepository;
import com.Blog.Mangement.BlogMangementSystem.entity.Category;
import com.Blog.Mangement.BlogMangementSystem.service.CategoryService;

@Service
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepository catRep;

	@Override
	public Category save(Category theCategory) {
		// TODO Auto-generated method stub
		return catRep.save(theCategory);
	}
	@Override
	public void deleteById(Long categoryId) {
		// TODO Auto-generated method stub
		catRep.deleteById(categoryId);
	}
	@Override
	public Category findByID(Long categoryId) {
		return catRep.findById(categoryId).orElseThrow
	(() -> new RuntimeException("Category not found with ID: " + categoryId));
	}

	@Override
	public java.util.List<Category> findAll() {
		return catRep.findAll();
	}
}

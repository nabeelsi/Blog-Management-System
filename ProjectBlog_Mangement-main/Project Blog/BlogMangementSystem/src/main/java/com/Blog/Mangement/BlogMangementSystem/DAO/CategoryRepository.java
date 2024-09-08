package com.Blog.Mangement.BlogMangementSystem.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Mangement.BlogMangementSystem.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}

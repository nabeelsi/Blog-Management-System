package com.Blog.Mangement.BlogMangementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Mangement.BlogMangementSystem.entity.Category;
import com.Blog.Mangement.BlogMangementSystem.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
	@Autowired
	private CategoryService CatServ;

	@GetMapping("/category/findAll")
	public ResponseEntity<?> findAll() {
		try {
			return new ResponseEntity<>(CatServ.findAll(), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/category/save")
	public ResponseEntity<?> addCategory(@RequestBody Category theCategory) {
		try {
			CatServ.save(theCategory);
			return new ResponseEntity<>("CategorySaved.. ", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<?> delCategory(@PathVariable ("categoryId") Long CategoryId) {
		try {
//		CatServ.findByID(CategoryId);
			CatServ.deleteById(CategoryId);
			return new ResponseEntity<>("categorydeleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/category/{categoryId}")

	public ResponseEntity<?> getCategory(@PathVariable ("categoryId")Long CategoryId) {
		try {
//	CatServ.findByID(CategoryId);

			return new ResponseEntity<>(CatServ.findByID(CategoryId), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}

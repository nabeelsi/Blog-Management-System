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

import com.Blog.Mangement.BlogMangementSystem.entity.Comment;
import com.Blog.Mangement.BlogMangementSystem.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	private CommentService ComServ;

	@GetMapping("/comment/findAll")

	public ResponseEntity<?> findAll() {
		try {
			// ComServ.findAll();

			return new ResponseEntity<>(ComServ.findAll(), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/comment/save")
	public ResponseEntity<?> addAuthor(@RequestBody Comment theComment) {
		try {
			ComServ.save(theComment);
			return new ResponseEntity<>("CommentSaved.. ", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<?> delAuthor(@PathVariable("commentId") Long CommentId) {
		try {
			// ComServ.findById(CommentId);
			ComServ.deleteById(CommentId);
			return new ResponseEntity<>("deletedid", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/comment/{commentId}")

	public ResponseEntity<?> getAtuhor(@PathVariable("commentId") Long CommentId) {
		try {
			// ComServ.findById(CommentId);

			return new ResponseEntity<>(ComServ.findById(CommentId), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}

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

import com.Blog.Mangement.BlogMangementSystem.entity.Author;
import com.Blog.Mangement.BlogMangementSystem.service.AuthorService;

@RestController
@RequestMapping("/api")
public class AuthorStudController {
	@Autowired
	private AuthorService AuthServ;

	@GetMapping("/author/findAll")
	public ResponseEntity<?> findAll() {
		try {

			return new ResponseEntity<>(AuthServ.findAll(), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/author/save")
	public ResponseEntity<?> addAuthor(@RequestBody Author theAuthor) {
		try {
			AuthServ.save(theAuthor);
			return new ResponseEntity<>("AuthorSaved.. ", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error occured "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/author/{authorId}")
	public ResponseEntity<?> delAuthor(@PathVariable Long authorId) {
		try {
//		AuthServ.findbyId(authorId);
			AuthServ.deleteById(authorId);
			return new ResponseEntity<>("authordeleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/author/{authorId}")

	public ResponseEntity<?> getAtuhor(@PathVariable Long authorId) {
		try {
			// AuthServ.findbyId(authorId);

			return new ResponseEntity<>(AuthServ.findbyId(authorId), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}

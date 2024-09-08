package com.Blog.Mangement.BlogMangementSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;

//package com.Blog.Mangement.BlogMangementSystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Mangement.BlogMangementSystem.entity.Post;
import com.Blog.Mangement.BlogMangementSystem.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
private PostService PoServ;

@GetMapping("/post/findAll")

public ResponseEntity<?> findAll(){
try {
	//PoServ.findAll();
	
	return new ResponseEntity<>(PoServ.findAll(),HttpStatus.FOUND);
}
catch(Exception e) {
	return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
}
}

@PostMapping("/post/save")
public ResponseEntity<?> addAuthor(@RequestBody Post thePost) {
	try {
		PoServ.save(thePost);
	return new ResponseEntity<>("PostSaved.. ",HttpStatus.CREATED);
	}
	catch(Exception e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}




@DeleteMapping("/post/{postId}")
public ResponseEntity<?> delAuthor(@PathVariable ("postId") Long PostId) {
	try {
		//PoServ.findByID(PostId);
		PoServ.deleteById(PostId);
	return new ResponseEntity<>("postdeleted",HttpStatus.OK);
	}
	catch(Exception e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}


@GetMapping("/post/{postId}")

public ResponseEntity<?> getAtuhor(@PathVariable ("postId") Long PostId){
try {
	//PoServ.findByID(PostId);
	
	return new ResponseEntity<>(PoServ.findByID(PostId),HttpStatus.FOUND);
}
catch(Exception e) {
	return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
}
}

}

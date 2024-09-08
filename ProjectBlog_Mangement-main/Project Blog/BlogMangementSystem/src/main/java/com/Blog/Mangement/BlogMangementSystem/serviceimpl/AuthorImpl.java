package com.Blog.Mangement.BlogMangementSystem.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Mangement.BlogMangementSystem.DAO.AuthorRepository;
import com.Blog.Mangement.BlogMangementSystem.entity.Author;
import com.Blog.Mangement.BlogMangementSystem.service.AuthorService;
//import com.Blog.Mangement.BlogMangementSystem.service.AuthorService;
@Service
public class AuthorImpl implements AuthorService{
	@Autowired
	private AuthorRepository authorRep;
//	public AuthorImpl(AuthorRepository theAuthorRep) {
//		authorRep=theAuthorRep;
//	}

	@Override
	public Author findbyId(Long authorId) {
		// TODO Auto-generated method stub
		Optional<Author> aut=authorRep.findById(authorId);
		Author theAuthor=null;
		if(aut.isPresent()) {
			theAuthor=aut.get();
		}
		return theAuthor;
	}

	@Override
	public Author save(Author theAuthor) {
		// TODO Auto-generated method stub
		return authorRep.save(theAuthor);
	}

	@Override
	public void deleteById(Long authorId) {
		// TODO Auto-generated method stub
		authorRep.deleteById(authorId);
	}
	@Override
	public java.util.List<Author> findAll(){
	return 	authorRep.findAll();
		
	}

}

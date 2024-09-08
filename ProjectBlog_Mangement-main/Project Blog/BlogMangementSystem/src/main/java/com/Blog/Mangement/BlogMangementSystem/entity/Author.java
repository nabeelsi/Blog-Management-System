package com.Blog.Mangement.BlogMangementSystem.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authorId;
	
	private String bio;

	@ManyToOne
	private UserEntity users;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "AuthorPostId")
	private List<Post> posts;

}

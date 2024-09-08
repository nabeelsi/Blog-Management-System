package com.Blog.Mangement.BlogMangementSystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	private String commentDate;
	private String commentText;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="commentUser",joinColumns=@JoinColumn(name="commentId"),
	inverseJoinColumns=@JoinColumn(name="userEntityId"))
	private java.util.List<UserEntity> users;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="commentPost",joinColumns=@JoinColumn(name="commentId"),
	inverseJoinColumns=@JoinColumn(name="PostId"))
    private java.util.List<Post> posts; 
}

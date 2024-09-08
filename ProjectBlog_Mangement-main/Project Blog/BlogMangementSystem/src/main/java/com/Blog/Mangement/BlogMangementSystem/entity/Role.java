package com.Blog.Mangement.BlogMangementSystem.entity;

//package com.Blog.Mangement.BlogMangementSystem.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.Blog.Mangement.BlogMangementSystem.util.ERole;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "role")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer id;

	  @Enumerated(EnumType.STRING)
	  @Column(length = 20)
	  private ERole name;

	  public Role() {

	  }

	public Role( ERole name) {
		this.name = name;
	}
	  
}
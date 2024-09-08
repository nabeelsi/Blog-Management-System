package com.Blog.Mangement.BlogMangementSystem.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Mangement.BlogMangementSystem.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity,Long>
{

	  
	Optional<UserEntity> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}

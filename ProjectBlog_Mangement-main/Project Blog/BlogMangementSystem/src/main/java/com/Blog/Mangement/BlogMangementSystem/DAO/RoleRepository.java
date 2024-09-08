package com.Blog.Mangement.BlogMangementSystem.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.Mangement.BlogMangementSystem.entity.Role;
import com.Blog.Mangement.BlogMangementSystem.util.ERole;

public interface RoleRepository extends JpaRepository<Role,Long> {
Optional<Role> findByName(ERole name);
}

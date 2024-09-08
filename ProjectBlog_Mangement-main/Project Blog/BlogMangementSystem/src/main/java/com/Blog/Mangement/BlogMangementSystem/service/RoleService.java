package com.Blog.Mangement.BlogMangementSystem.service;

import com.Blog.Mangement.BlogMangementSystem.entity.Role;

public interface RoleService {
	Role save(Role theRole);
	Role findById(Long roleId);
	java.util.List<Role> findAll();
	void deleteById(Long roleId);
}

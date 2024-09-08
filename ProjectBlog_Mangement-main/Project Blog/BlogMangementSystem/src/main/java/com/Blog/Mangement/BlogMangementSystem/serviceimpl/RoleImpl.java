package com.Blog.Mangement.BlogMangementSystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.Mangement.BlogMangementSystem.DAO.RoleRepository;
import com.Blog.Mangement.BlogMangementSystem.entity.Role;
import com.Blog.Mangement.BlogMangementSystem.service.RoleService;
@Service
public class RoleImpl implements RoleService{
@Autowired
private RoleRepository roleRp;

	@Override
	public Role save(Role theRole) {
		// TODO Auto-generated method stub
		return roleRp.save(theRole);
	}

	@Override
	public Role findById(Long roleId) {

		return roleRp.findById(roleId).orElseThrow
				(()-> new RuntimeException
		("role not found with ID: "+roleId)); 
	}

	@Override
	public void deleteById(Long roleId) {
		// TODO Auto-generated method stub
		roleRp.deleteById(roleId);
	}
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRp.findAll();
	}

}

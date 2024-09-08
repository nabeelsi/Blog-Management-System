package com.Blog.Mangement.BlogMangementSystem.serviceimpl;

//package com.Blog.Mangement.BlogMangementSystem.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Blog.Mangement.BlogMangementSystem.DAO.UserEntityRepository;
import com.Blog.Mangement.BlogMangementSystem.entity.UserEntity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	  @Autowired
	  UserEntityRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity user= userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException
				("User not found with username: " + username));
		return UserDetailsImpl.build(user);
	}


}
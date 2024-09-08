package com.Blog.Mangement.BlogMangementSystem.security.util;
//package com.Blog.Mangement.BlogMangementSystem.security.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Blog.Mangement.BlogMangementSystem.serviceimpl.UserDetailsServiceImpl;


//using jwt user authentication (2nd step)
@Component
public class AuthFilter  extends OncePerRequestFilter {
	
	@Autowired
	JwtTokenUtill jwtTokenUtill;
	
	  @Autowired
	  private UserDetailsServiceImpl userDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

	//It is responsible for performing filtering logic on incoming HTTP requests. In this case, 
	//it's used for JWT authentication.

	//by jwt we are giving authenitiction to user
	  @SuppressWarnings("null")
	@Override
	  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	      throws ServletException, IOException {
	    try {
	      String jwt = parseJwt(request);
	      if (jwt != null && jwtTokenUtill.validateJwtToken(jwt)) {
	        String username = jwtTokenUtill.getUserNameFromJwtToken(jwt);

			//gen authtn token by jwt
	        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	        UsernamePasswordAuthenticationToken authentication =
	            new UsernamePasswordAuthenticationToken(
	                userDetails,
	                null,
	                userDetails.getAuthorities());

					//This sets additional details on the authentication token, such as the user's IP address, session ID, etc., by building details from the current HTTP request
	        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	      }
	    } catch (Exception e) {
	      logger.error("Cannot set user authentication: {}", e);
	    }

		//continue filter chain
	    filterChain.doFilter(request, response);
	  }


	  //returnig jwt token
	  private String parseJwt(HttpServletRequest request) {
	    String headerAuth = request.getHeader("Authorization");

	    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
	      return headerAuth.substring(7, headerAuth.length());
	    }

	    return null;
	  }
	  
}
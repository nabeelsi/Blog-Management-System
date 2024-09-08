package com.Blog.Mangement.BlogMangementSystem.security.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.Blog.Mangement.BlogMangementSystem.response.JwtResponse;
import com.Blog.Mangement.BlogMangementSystem.serviceimpl.UserDetailsImpl;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

// generate jwt,validate jwt,get username from jwt token
 
@Component
public class JwtTokenUtill {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtill.class);

	@Value("${hms.api.jwtSecret}")
	private String jwtSecret;

	@Value("${hms.api.jwtExpirationMs}")
	private int jwtExpirationMs;

	public JwtResponse generateJwtToken(Authentication authentication) {
		JwtResponse jwtResponse = new JwtResponse();

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		//
		String token = Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

		jwtResponse.setToken(token);	
		jwtResponse
				.setExpieryTime(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration());
		jwtResponse.setType("Bearer");

		return jwtResponse;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (io.jsonwebtoken.SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}

//generateJwtToken
//getUsernameFromJwtToken
//validateJwtToken - boolean, exception 
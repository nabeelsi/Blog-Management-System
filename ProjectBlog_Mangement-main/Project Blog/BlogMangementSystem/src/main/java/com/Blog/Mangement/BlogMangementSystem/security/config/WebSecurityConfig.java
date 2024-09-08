package com.Blog.Mangement.BlogMangementSystem.security.config;

//package com.Blog.Mangement.BlogMangementSystem.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Blog.Mangement.BlogMangementSystem.security.util.AuthEntryPointJwt;
import com.Blog.Mangement.BlogMangementSystem.security.util.AuthFilter;
import com.Blog.Mangement.BlogMangementSystem.serviceimpl.UserDetailsServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(
    // securedEnabled = true,
    // jsr250Enabled = true,
    prePostEnabled = true)
public class WebSecurityConfig {
	
	  @Autowired
	  UserDetailsServiceImpl userDetailsService;

	  @Autowired
	  private AuthEntryPointJwt unauthorizedHandler;
	  
	  @Bean
	  public AuthFilter authenticationJwtTokenFilter() {
	    return new AuthFilter();
	  }
	  
	  @Bean//encoding password , extracting username from UserDetailsServiceimpl and providing authentication by Dao auth provuder. //this method is continue with security filter chain
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }
	  

	  //Login Process: Jab user /api/auth/** par login request karta hai, AuthenticationManager automatically trigger hota hai. Ye user ke provided credentials ko validate karta hai using the configured DaoAuthenticationProvider. Agar credentials sahi hain, to user ko authenticated kiya jata hai aur session ke bina, har request ke liye token-based authentication manage hoti hai.
	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }

	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	  

	  //3 urls permit/authorised and rest required authentication 
	  //handle un authorised users
	  //encoding password , extracting username
	  //disable CORS(dont block diff domains request) and CSRF(no extra validation on form submission)
	  //provide username password by authentication provider
	  //adding our filter to the filter chain
	  @Bean 
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.cors().and().csrf().disable()
		    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	        .authorizeRequests().antMatchers("/api/auth/**").permitAll()
	        .antMatchers("/api/test/**").permitAll()
	        .antMatchers("/api/agent/**").permitAll()
	        .anyRequest().authenticated();
	    
	    http.authenticationProvider(authenticationProvider()); 

		
	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	    
	    return http.build();
	    	
	  }
}

//permit links , other authenticated
//session policy
//excpetion handling - unauthorised users

//authenticatiin manageer - automatic trigger and use dao to validate users
//password,userdetails - fetch and encode

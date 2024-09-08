package com.Blog.Mangement.BlogMangementSystem.response;

//package com.Blog.Mangement.BlogMangementSystem.response;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse implements Serializable {

	
private static final long serialVersionUID = 1L;
private String token;
private String type = "Bearer";
private Date expieryTime;
  
  public JwtResponse(String accessToken) {
	  
	  this.token = accessToken;
  }

  public JwtResponse() {
	  
  }
}
package com.Blog.Mangement.BlogMangementSystem.response;

//public class MessageResponse {
//
//}
//package com.Blog.Mangement.BlogMangementSystem.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageResponse implements Serializable{
	
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String message;

//  public MessageResponse(String message) {
//    this.message = message;
//  }



public MessageResponse(String message) {
	// TODO Auto-generated constructor stub
	this.message=message;
}

}
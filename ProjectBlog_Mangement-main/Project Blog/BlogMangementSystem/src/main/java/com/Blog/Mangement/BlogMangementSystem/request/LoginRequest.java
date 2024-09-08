package com.Blog.Mangement.BlogMangementSystem.request;
//
//public class LoginRequest {
//
//}
//package com.Blog.Mangement.BlogMangementSystem.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest implements Serializable {

	private static final
	long serialVersionUID = 1L;

	@NotBlank
    private String username;

	@NotBlank
	private String password;
}
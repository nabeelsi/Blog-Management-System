package com.Blog.Mangement.BlogMangementSystem.Controller;

//Opackage com.Blog.Mangement.BlogMangementSystem.Controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Mangement.BlogMangementSystem.DAO.RoleRepository;
import com.Blog.Mangement.BlogMangementSystem.DAO.UserEntityRepository;
import com.Blog.Mangement.BlogMangementSystem.entity.Role;
import com.Blog.Mangement.BlogMangementSystem.entity.UserEntity;
import com.Blog.Mangement.BlogMangementSystem.request.LoginRequest;
import com.Blog.Mangement.BlogMangementSystem.request.SignupRequest;
import com.Blog.Mangement.BlogMangementSystem.response.JwtResponse;
import com.Blog.Mangement.BlogMangementSystem.response.MessageResponse;
import com.Blog.Mangement.BlogMangementSystem.security.util.JwtTokenUtill;
import com.Blog.Mangement.BlogMangementSystem.util.ERole;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	@Autowired
	UserEntityRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtTokenUtill jwtTokenUtill;

	@PostMapping("/v1/signin")//sign in and return jwt token
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		JwtResponse jwt = jwtTokenUtill.generateJwtToken(authentication);

		return ResponseEntity.ok((jwt));

	}           

	@PostMapping("/v1/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		UserEntity user = new UserEntity(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getEmail());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "agent":
					Role modRole = roleRepository.findByName(ERole.ROLE_AGENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
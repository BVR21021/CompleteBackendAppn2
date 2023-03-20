package com.venky.controller;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venky.payload.LoginDto;
import com.venky.payload.UserDto;
import com.venky.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	
	
	
	
	

	//@Autowired
	//private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userdto) {

		return new ResponseEntity<>(userService.createUser(userdto), HttpStatus.CREATED);
	}
	
	

	/*
	 * @PostMapping("/login") public ResponseEntity<String> loginUser(@RequestBody
	 * LoginDto loginDto) {
	 * 
	 * Authentication authentication = authenticationManager .authenticate(new
	 * UsernamePasswordAuthenticationToken(loginDto.getEmail(),
	 * loginDto.getPassword()));
	 * SecurityContextHolder.getContext().setAuthentication(authentication);
	 * 
	 * return new ResponseEntity<>("User Logged in Successfully", HttpStatus.OK); }
	 */
}

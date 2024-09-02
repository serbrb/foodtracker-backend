package com.food.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.tracker.entity.User;
import com.food.tracker.request.dto.LoginDTO;
import com.food.tracker.request.dto.UserDTO;
import com.food.tracker.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
		User user = authService.loginUser(loginDTO);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO) {
		User newUser = authService.registerUser(userDTO);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<User>> loginUser() {
		List<User> allUser = authService.getAllUser();
		return new ResponseEntity<>(allUser, HttpStatus.OK);
	}
}

package com.food.tracker.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.tracker.entity.User;
import com.food.tracker.exception.AlreadyExistsException;
import com.food.tracker.exception.AppException;
import com.food.tracker.exception.NotFoundException;
import com.food.tracker.repository.UserRepository;
import com.food.tracker.request.dto.LoginDTO;
import com.food.tracker.request.dto.UserDTO;

@Service
public class AuthService {

	@Autowired
	UserRepository userRepo;

	public User loginUser(LoginDTO loginDTO) {
		try {
			User user = userRepo.findByUserNameAndPassword(loginDTO.getUserName(), loginDTO.getPassword());
			if (user != null) {
				return user;
			} else {
				throw new NotFoundException("Bad credentials..!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	public User registerUser(UserDTO userDTO) {
		try {
			User isUserName = userRepo.findByUserName(userDTO.getUserName());
			if (isUserName != null) {
				throw new AlreadyExistsException("User name id already exists");
			}
			User newUser = new User();
			newUser.setFirstName(userDTO.getFirstName());
			newUser.setLastName(userDTO.getLastName());
			newUser.setPassword(userDTO.getPassword());
			newUser.setUserName(userDTO.getUserName());
			newUser.setCreatedAt(LocalDateTime.now());
			return userRepo.save(newUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	public List<User> getAllUser() {
		try {
			return userRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

}

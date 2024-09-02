package com.food.tracker.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.tracker.entity.FoodDetails;
import com.food.tracker.request.dto.FoodDetailsDTO;
import com.food.tracker.response.dto.FoodDetailsResponseDTO;
import com.food.tracker.response.dto.MessageDTO;
import com.food.tracker.service.FoodDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/food-details")
@CrossOrigin("*")
public class FoodDetailsController {

	@Autowired
	private FoodDetailsService foodDetailsService;

	@PostMapping
	public ResponseEntity<FoodDetails> createFoodDetails(@Valid @RequestBody FoodDetailsDTO foodDetailsDTO) {
		FoodDetails foodDetails = foodDetailsService.createFoodDetails(foodDetailsDTO);
		return new ResponseEntity<>(foodDetails, HttpStatus.CREATED);
	}

	@PutMapping("/{foodDetailsId}")
	public ResponseEntity<FoodDetails> updateFoodDetails(@PathVariable("foodDetailsId") Long foodDetailsId,
			@Valid @RequestBody FoodDetailsDTO foodDetailsDTO) {
		FoodDetails foodDetails = foodDetailsService.updateFoodDetails(foodDetailsId, foodDetailsDTO);
		return new ResponseEntity<>(foodDetails, HttpStatus.OK);
	}

	@GetMapping("/{foodDetailsId}")
	public ResponseEntity<FoodDetails> getFoodDetails(@PathVariable("foodDetailsId") Long foodDetailsId) {
		FoodDetails foodDetails = foodDetailsService.getFoodDetails(foodDetailsId);
		return new ResponseEntity<>(foodDetails, HttpStatus.OK);
	}

	@GetMapping("/{userId}/{date}")
	public ResponseEntity<FoodDetailsResponseDTO> getAllFoodDetails(@PathVariable("userId") Long userId,
			@PathVariable("date") LocalDate date) {
		FoodDetailsResponseDTO foodDetailsResponse = foodDetailsService.getAllFoodDetails(userId, date);
		return new ResponseEntity<>(foodDetailsResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{foodDetailsId}/{userId}")
	public ResponseEntity<MessageDTO> deleteFoodDetails(@PathVariable("foodDetailsId") Long foodDetailsId,
			@PathVariable("userId") Long userId) {
		String message = foodDetailsService.deleteFoodDetails(foodDetailsId, userId);
		MessageDTO messageDTO = new MessageDTO(message, LocalDateTime.now());
		return new ResponseEntity<>(messageDTO, HttpStatus.OK);
	}
}

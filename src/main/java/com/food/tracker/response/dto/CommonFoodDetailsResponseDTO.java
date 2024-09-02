package com.food.tracker.response.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.food.tracker.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonFoodDetailsResponseDTO {

	private Long id;

	private String name;

	private String description;

	private long calories;

	private long quantity;

	private User createdBy;
	
	private LocalDate date;

	private LocalTime localTime;

	private double totalCalories;

}

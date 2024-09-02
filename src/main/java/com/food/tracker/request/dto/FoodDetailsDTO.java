package com.food.tracker.request.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDetailsDTO {

	@NotNull(message = "Name is required")
	@Size(max = 100, message = "Name should not exceed 100 characters")
	private String name;

	@NotNull(message = "Description is required")
	@Size(max = 500, message = "Description should not exceed 500 characters")
	private String description;

	@NotNull(message = "Calories is required")
	private Long calories;

	@NotNull(message = "Quantity is required")
	private Long quantity;

	@NotNull(message = "UserId is required")
	private Long userId;

	@NotNull(message = "Date is required")
	private LocalDate date;

	@NotNull(message = "Time is required")
	private LocalTime time;

}

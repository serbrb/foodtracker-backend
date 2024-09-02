package com.food.tracker.response.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDetailsResponseDTO {

	private double TodayTotalCalories;

	private List<CommonFoodDetailsResponseDTO> foodDetails;

}

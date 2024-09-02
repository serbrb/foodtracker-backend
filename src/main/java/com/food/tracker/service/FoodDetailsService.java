package com.food.tracker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.tracker.entity.FoodDetails;
import com.food.tracker.entity.User;
import com.food.tracker.exception.AppException;
import com.food.tracker.exception.NotFoundException;
import com.food.tracker.repository.FoodDetailsRepository;
import com.food.tracker.repository.UserRepository;
import com.food.tracker.request.dto.FoodDetailsDTO;
import com.food.tracker.response.dto.CommonFoodDetailsResponseDTO;
import com.food.tracker.response.dto.FoodDetailsResponseDTO;

@Service
public class FoodDetailsService {

	@Autowired
	FoodDetailsRepository foodDetailsRepo;

	@Autowired
	UserRepository userRepo;

	public FoodDetails createFoodDetails(FoodDetailsDTO foodDetailsDTO) {
		try {
			User user = userRepo.findById(foodDetailsDTO.getUserId())
					.orElseThrow(() -> new NotFoundException("User id not found"));
			FoodDetails foodDetails = new FoodDetails();
			foodDetails.setName(foodDetailsDTO.getName());
			foodDetails.setDescription(foodDetailsDTO.getDescription());
			foodDetails.setCalories(foodDetailsDTO.getCalories());
			foodDetails.setQuantity(foodDetailsDTO.getQuantity());
			foodDetails.setCreatedBy(user);
			foodDetails.setDate(foodDetailsDTO.getDate());
			foodDetails.setLocalTime(foodDetailsDTO.getTime());
			return foodDetailsRepo.save(foodDetails);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	public FoodDetails updateFoodDetails(Long foodDetailsId, FoodDetailsDTO foodDetailsDTO) {
		try {
			FoodDetails foodDetails = foodDetailsRepo.findByIdAndCreatedById(foodDetailsId, foodDetailsDTO.getUserId());
			if (foodDetails == null) {
				throw new NotFoundException("You don't have permission to update food details");
			}
			foodDetails.setName(foodDetailsDTO.getName());
			foodDetails.setDescription(foodDetailsDTO.getDescription());
			foodDetails.setCalories(foodDetailsDTO.getCalories());
			foodDetails.setQuantity(foodDetailsDTO.getQuantity());
			foodDetails.setDate(foodDetailsDTO.getDate());
			foodDetails.setLocalTime(foodDetailsDTO.getTime());
			return foodDetailsRepo.save(foodDetails);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	public FoodDetails getFoodDetails(Long foodDetailsId) {
		try {
			return foodDetailsRepo.findById(foodDetailsId)
					.orElseThrow(() -> new NotFoundException("FoodDetails id not found"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	public FoodDetailsResponseDTO getAllFoodDetails(Long userId, LocalDate date) {
		try {
			List<FoodDetails> foodDetails = foodDetailsRepo.findAllByCreatedByIdAndDate(userId, date);
			long todayTotalCalories = 0;

			List<CommonFoodDetailsResponseDTO> listOfFoodDetails = new ArrayList<>();

			for (FoodDetails details : foodDetails) {
				CommonFoodDetailsResponseDTO responseDTO = new CommonFoodDetailsResponseDTO();
				responseDTO.setId(details.getId());
				responseDTO.setName(details.getName());
				responseDTO.setDescription(details.getDescription());
				responseDTO.setCalories(details.getCalories());
				responseDTO.setQuantity(details.getQuantity());
				responseDTO.setCreatedBy(details.getCreatedBy());
				responseDTO.setDate(details.getDate());
				responseDTO.setLocalTime(details.getLocalTime());
				double totalCalories = (details.getCalories() * details.getQuantity()) / 100;
				responseDTO.setTotalCalories(totalCalories);
				todayTotalCalories += totalCalories;
				listOfFoodDetails.add(responseDTO);
			}

			FoodDetailsResponseDTO responseDTO = new FoodDetailsResponseDTO();
			responseDTO.setTodayTotalCalories(todayTotalCalories);
			responseDTO.setFoodDetails(listOfFoodDetails);
			return responseDTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	public String deleteFoodDetails(Long foodDetailsId, Long userId) {
		try {
			FoodDetails foodDetails = foodDetailsRepo.findByIdAndCreatedById(foodDetailsId, userId);
			if (foodDetails == null) {
				throw new NotFoundException("You don't have permission to delete food details");
			}
			foodDetailsRepo.deleteById(foodDetailsId);
			return "Food details deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

}

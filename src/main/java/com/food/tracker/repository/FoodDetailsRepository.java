package com.food.tracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.tracker.entity.FoodDetails;

@Repository
public interface FoodDetailsRepository extends JpaRepository<FoodDetails, Long> {

	FoodDetails findByIdAndCreatedById(Long foodDetailsId, Long userId);

	List<FoodDetails> findAllByCreatedById(Long userId);

	List<FoodDetails> findAllByCreatedByIdAndDate(Long userId, LocalDate date);

}

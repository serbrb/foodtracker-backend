package com.food.tracker.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "food_details")
public class FoodDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "description", length = 500)
	private String description;

	// 100Grams
	@Column(name = "calories")
	private long calories;

	//Grams
	@Column(name = "quantity")
	private long quantity;

	@ManyToOne
	private User createdBy;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "time")
	private LocalTime localTime;

}

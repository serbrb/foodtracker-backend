package com.food.tracker.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", length = 25)
	private String firstName;

	@Column(name = "last_name", length = 25)
	private String lastName;

	@Column(name = "user_name", length = 100)
	private String userName;

	@JsonIgnore
	@Column(name = "password", length = 50)
	private String password;

	@JsonIgnore
	@Column(name = "created_at")
	private LocalDateTime createdAt;

}

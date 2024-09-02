package com.food.tracker.request.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

	@NotEmpty(message = "userName is required")
	private String userName;

	@NotEmpty(message = "password is required")
	private String password;

}

package com.food.tracker.request.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	@NotNull(message = "First name is required")
	private String firstName;

	@NotNull(message = "Last name is required")
	private String lastName;

	@NotNull(message = "Email id is required")
	private String userName;

	@NotEmpty(message = "password is required")
	private String password;

}

package com.food.tracker.response.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageDTO {

	private String message;

	private LocalDateTime dateTime;

}

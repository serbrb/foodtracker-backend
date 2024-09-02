package com.food.tracker.exception;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private String status;
	private String message;
	private long timeStamp;
	
	
}
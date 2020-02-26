package com.prudential.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

public class ResponseHandling {
	
	private String timestamp;
	private HttpStatus status;
	private String message;

	public ResponseHandling() {
		timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getTime() {
		return timestamp;
	}

}

package com.prudential.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;

public class ErrorHandling {

	private String timestamp;
	private HttpStatus status;
	private String message;
	private String debugMessage;

	public ErrorHandling() {
		timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}
	

	public HttpStatus getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getDebugMessage() {
		return debugMessage;
	}
	
	public String getTime() {
		return timestamp;
	}
}

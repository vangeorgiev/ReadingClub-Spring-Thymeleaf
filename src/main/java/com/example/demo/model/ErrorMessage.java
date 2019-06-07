package com.example.demo.model;

import java.time.LocalDateTime;

public class ErrorMessage {
	private int status;
	private String message;
	private LocalDateTime exceptionTime;

	public ErrorMessage(int status, String message, LocalDateTime exceptionTime) {
		this.status = status;
		this.message = message;
		this.exceptionTime = exceptionTime;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getExceptionTime() {
		return exceptionTime;
	}

}

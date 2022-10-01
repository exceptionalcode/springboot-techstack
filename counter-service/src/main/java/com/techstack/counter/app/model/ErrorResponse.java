package com.techstack.counter.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private String timestamp;
	private int status;
	private String error;
	private String path;
	private String cause;
	private String requestId;
}

package com.techstack.counter.app.exception;

import com.techstack.counter.app.model.ErrorResponse;
import com.techstack.counter.app.utility.DateTimeUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = CounterExistException.class)
	public ResponseEntity<ErrorResponse> handleCounterExistException(CounterExistException exception, WebRequest webRequest) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.timestamp(DateTimeUtil.getCurrentDateTime())
				.status(HttpStatus.CONFLICT.value())
				.error(HttpStatus.CONFLICT.getReasonPhrase())
				.path(webRequest.getDescription(false).replace("uri=", Strings.EMPTY))
				.cause("Counter Already Exist")
				.requestId(webRequest.getHeader("requestId"))
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleFieldValidationException(MethodArgumentNotValidException exception, WebRequest webRequest) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.timestamp(DateTimeUtil.getCurrentDateTime())
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.path(webRequest.getDescription(false).replace("uri=", Strings.EMPTY))
				.cause("Wrong : " + exception.getFieldError().getField())
				.requestId(webRequest.getHeader("requestId"))
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = CounterNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCounterNotFoundException(CounterNotFoundException exception, WebRequest webRequest) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.timestamp(DateTimeUtil.getCurrentDateTime())
				.status(HttpStatus.NOT_FOUND.value())
				.error(HttpStatus.NOT_FOUND.getReasonPhrase())
				.path(webRequest.getDescription(false).replace("uri=", Strings.EMPTY))
				.cause("Counter Not Found")
				.requestId(webRequest.getHeader("requestId"))
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}

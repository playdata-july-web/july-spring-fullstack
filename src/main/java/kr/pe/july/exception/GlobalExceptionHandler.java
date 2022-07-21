package kr.pe.july.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = {
			EntityNotFoundException.class, 
			SearchResultNotFoundException.class, 
			DuplicateUserException.class
	})
	public String EntityNotFoundExceptionHandler(Exception e) {
		return e.getMessage();
	}
}

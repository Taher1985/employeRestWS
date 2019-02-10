package com.demo.emp.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.emp.model.EmployeeErrorResponse;


@ControllerAdvice
public class EmployeeGlobalExceptionHandler {
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public @ResponseBody ResponseEntity<EmployeeErrorResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception, HttpServletRequest request) {
		EmployeeErrorResponse error = new EmployeeErrorResponse();
		error.setErrorCode(404);
		error.setMessage("No Records found");
		return new ResponseEntity<EmployeeErrorResponse>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(SQLException.class)
	public @ResponseBody ResponseEntity<EmployeeErrorResponse> handleSQLException(SQLException exception, HttpServletRequest request) {
		EmployeeErrorResponse error = new EmployeeErrorResponse();
		if(exception instanceof SQLException) {
			error.setErrorCode(500);
			error.setMessage(exception.getMessage());
			return new ResponseEntity<EmployeeErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		error.setErrorCode(500);
		error.setMessage(exception.getMessage());
		return new ResponseEntity<EmployeeErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

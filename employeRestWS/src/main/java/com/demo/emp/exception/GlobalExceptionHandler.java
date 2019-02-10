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
import com.demo.emp.util.EmployeeUtil;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public @ResponseBody ResponseEntity<EmployeeErrorResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception, HttpServletRequest request) {
		EmployeeErrorResponse error = new EmployeeErrorResponse();
		HttpStatus status = EmployeeUtil.getStatus(request);
		error.setErrorCode(status.value());
		error.setMessage("No Records found");
		return new ResponseEntity<EmployeeErrorResponse>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(SQLException.class)
	public @ResponseBody ResponseEntity<EmployeeErrorResponse> handleSQLException(SQLException exception, HttpServletRequest request) {
		EmployeeErrorResponse error = new EmployeeErrorResponse();
		HttpStatus status = EmployeeUtil.getStatus(request);
		if(exception instanceof SQLException) {
			error.setErrorCode(status.value());
			error.setMessage(exception.getMessage());
			return new ResponseEntity<EmployeeErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		error.setErrorCode(status.value());
		error.setMessage(exception.getMessage());
		return new ResponseEntity<EmployeeErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

package com.demo.emp.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

public class EmployeeUtil {

	public static HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}

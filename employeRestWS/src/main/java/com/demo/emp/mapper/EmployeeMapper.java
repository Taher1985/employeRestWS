package com.demo.emp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.demo.emp.model.Employee;

public class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setEmployeeId(rs.getString("EMP_ID"));
		employee.setEmployeeName(rs.getString("EMP_NAME"));
		employee.setEmployeeEmail(rs.getString("EMAIL_ID"));
		employee.setEmployeeCity(rs.getString("CITY"));
		employee.setEmployeeCompany(rs.getString("COMPANY"));
		employee.setEmployeeDOJ(rs.getDate("DATE_OF_JOINING"));
		return employee;
	}

}

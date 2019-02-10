package com.demo.emp.dao;

import java.util.List;

import com.demo.emp.model.Employee;

public interface EmployeeDao {
	public Employee addEmployee(Employee employee) throws Exception;
	public Employee getEmployee(String employeeId) throws Exception;
	public List<Employee> getEmployeeList() throws Exception;
	public int updateEmployee(Employee employee) throws Exception;
	public int deleteEmployee(String employeeId) throws Exception;
}

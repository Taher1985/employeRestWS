package com.demo.emp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.emp.model.Employee;
import com.demo.emp.model.EmployeeErrorResponse;
import com.demo.emp.service.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeController {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	private static final Integer ERORR_CODE = 1;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/getEmployeeList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Employee> getEmployeeList() throws Exception {
		LOG.info("In getEmployeeList()");
		Employee employee = new Employee();
		List<Employee> employeesList = employeeService.getEmployeeList();
		employee.setEmployeeList(employeesList);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/getEmployee/{employeeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Employee> getEmployee(@PathVariable String employeeId) throws Exception {
		LOG.info("In getEmployee()");
		Employee employee = employeeService.getEmployee(employeeId);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public ResponseEntity<EmployeeErrorResponse> addEmployee(@RequestBody Employee employee) throws Exception {
		LOG.info("In addEmployee()");
		EmployeeErrorResponse response = new EmployeeErrorResponse();
		employee = employeeService.addEmployee(employee);
		response.setMessage("Employee ID " + employee.getEmployeeId() + " added");
		return new ResponseEntity<EmployeeErrorResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT)
	public ResponseEntity<EmployeeErrorResponse> updateEmployee(@RequestBody Employee employee) throws Exception {
		LOG.info("In updateEmployee()");
		EmployeeErrorResponse response = new EmployeeErrorResponse();
		employeeService.updateEmployee(employee);
		response.setMessage("Employee ID " + employee.getEmployeeId() + " updated");
		return new ResponseEntity<EmployeeErrorResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteEmployee/{employeeId}", method = RequestMethod.DELETE)
	public ResponseEntity<EmployeeErrorResponse> deleteEmployee(@PathVariable String employeeId) throws Exception {
		LOG.info("In deleteEmployee()");
		EmployeeErrorResponse response = new EmployeeErrorResponse();
		employeeService.deleteEmployee(employeeId);
		response.setMessage("Employee ID " + employeeId + " deleted");
		return new ResponseEntity<EmployeeErrorResponse>(response, HttpStatus.OK);
	}
}

package com.demo.emp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.emp.dao.EmployeeDao;
import com.demo.emp.model.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee addEmployee(Employee employee) throws Exception {
		LOG.info("In service add()");
		employee = employeeDao.addEmployee(employee);
		return employee;
	}

	@Override
	public Employee getEmployee(String employeeId) throws Exception {
		LOG.info("In service get()");
		Employee employee = employeeDao.getEmployee(employeeId);
		return employee;
	}

	@Override
	public List<Employee> getEmployeeList() throws Exception {
		LOG.info("In service getEmployeeList()");
		List<Employee> employeesList = employeeDao.getEmployeeList();
		return employeesList;
	}

	@Override
	public int updateEmployee(Employee employee) throws Exception {
		LOG.info("In service update()");
		int record = employeeDao.updateEmployee(employee);
		return record;
	}

	@Override
	public int deleteEmployee(String employeeId) throws Exception {
		LOG.info("In service delete()");
		int record = employeeDao.deleteEmployee(employeeId);
		return record;
	}
}

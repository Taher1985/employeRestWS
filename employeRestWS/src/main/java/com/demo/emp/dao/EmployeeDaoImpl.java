package com.demo.emp.dao;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.emp.mapper.EmployeeMapper;
import com.demo.emp.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Employee addEmployee(Employee employee) throws Exception {
		LOG.info("In dao add()");
		try {
			String query = "insert into employee values(?,?,?,?,?,?)";
			int record = jdbcTemplate.update(query, employee.getEmployeeId(), employee.getEmployeeName(),
					employee.getEmployeeEmail(), employee.getEmployeeCity(), employee.getEmployeeCompany(),
					employee.getEmployeeDOJ());
			LOG.info(record + " record inserted");
		} catch (Exception e) {
			LOG.error("Employee ID already exist: ", e);
			throw new SQLException("Employee ID already exist");
		}
		return employee;
	}

	@Override
	public Employee getEmployee(String employeeId) throws Exception {
		LOG.info("In dao get()");
		String query = "select * from employee where EMP_ID = '" + employeeId + "'";
		Employee employee = jdbcTemplate.queryForObject(query, new EmployeeMapper());
		if (employee != null) {
			return employee;
		} else {
			throw new EmptyResultDataAccessException("Employee ID does not exist", 0);
		}
	}

	@Override
	public List<Employee> getEmployeeList() throws Exception {
		LOG.info("In dao getEmployeeList()");
		String query = "select * from employee";
		List<Employee> employeesList = jdbcTemplate.query(query, new EmployeeMapper());
		if (employeesList.size() > 0) {
			return employeesList;
		} else {
			throw new EmptyResultDataAccessException("No Records found", 0);
		}
	}

	@Override
	public int updateEmployee(Employee employee) throws Exception {
		LOG.info("In dao update()");
		int record;
		Employee empExist = getEmployee(employee.getEmployeeId());
		if (empExist != null) {
			String query = "update employee set EMP_NAME = ?, EMAIL_ID = ?, CITY = ?, COMPANY = ?, DATE_OF_JOINING = ? where EMP_ID = ?";
			record = jdbcTemplate.update(query, employee.getEmployeeName(), employee.getEmployeeEmail(),
					employee.getEmployeeCity(), employee.getEmployeeCompany(), employee.getEmployeeDOJ(),
					employee.getEmployeeId());
			return record;
		} else {
			throw new EmptyResultDataAccessException("No Records found", 0);
		}

	}

	@Override
	public int deleteEmployee(String employeeId) throws Exception {
		LOG.info("In dao delete()");
		int record;
		String query = "delete from employee where EMP_ID = ?";
		record = jdbcTemplate.update(query, employeeId);
		return record;
	}
}

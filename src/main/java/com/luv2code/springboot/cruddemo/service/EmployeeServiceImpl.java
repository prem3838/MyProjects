package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDAO employeeDao;
	//inject constructor injection
	@Autowired
	public EmployeeServiceImpl(EmployeeDAO employeeDao) {
		this.employeeDao=employeeDao;
	}

	@Override
	@Transactional
	public List<Employee> findall() {
		return employeeDao.findAll();
	}

	@Override
	@Transactional
	public Employee findEmployee(int theId) {
		return employeeDao.findEmployee(theId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		employeeDao.save(theEmployee);

	}

	@Override
	@Transactional
	public void deleteById(int theId) {
	employeeDao.deleteById(theId);
	}

}

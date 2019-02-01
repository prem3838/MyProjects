package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	private EmployeeService employeeService;
	//quick and dirty:
	//inject the employee the employee dao(use constructor ijection)

	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
		
	//expose the /employee endpointand return list of end points
	@GetMapping("/employee")
	public List<Employee> findAll(){
		return employeeService.findall();
	}
	@GetMapping("/employee/{empID}")
	public Employee findById(@PathVariable int empID) {
		Employee tempemp= employeeService.findEmployee(empID);
		if(tempemp==null) {
			throw new RuntimeException("employee with the id is not found" + empID);
		}
		return tempemp;
	}
	//requestbody annotation is used to write the employee body from json
	//adding employee
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		//also just in case if they pass an id in the json make it to id=0
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}
	//update exsting employee
	@PutMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeService.save(theEmployee);
		return theEmployee;
	}
	@DeleteMapping("/employee/{empId}")
	public String deleteEmployee(@PathVariable int empId) {
		Employee emp=employeeService.findEmployee(empId);
		if(emp==null)
		{
			throw new RuntimeException("the employee not found with id: " + empId);
			
		}
		employeeService.deleteById(empId);
		return "deleting employee with id: " + empId;
	}

}

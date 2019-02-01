package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;
@Repository
public class EmployeeDAO_impl implements EmployeeDAO {
	//define entity manager
	private EntityManager entityManager;
	
	//setup up constructor injection
	@Autowired
	public EmployeeDAO_impl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	//we add @transactional so we do not need to add the begin and aend transaction
	//but we comment it if we use the service Dao
	//@Transactional
	public List<Employee> findAll() {
		//get current hibernate session
		Session currentSession=entityManager.unwrap(Session.class);
		
		
		//create the query
		List<Employee> employees=currentSession.createQuery("from Employee ",Employee.class).list();
		
		
		//execute query and get the result
		//return the result like c,r,u,d
		
		return employees;
	}

	//find employee by Id
	@Override
	public Employee findEmployee(int theId) {
		//get current hibernate session
		Session currentSession=entityManager.unwrap(Session.class);
		//get the employee with id
		Employee tempemployee=currentSession.get(Employee.class,theId);
		//return the employee
		return tempemployee;
		
	}
	
	//for saving or updating the employee
	@Override
	public void save(Employee theEmployee) {
		//get current hibernate session
		Session currentSession=entityManager.unwrap(Session.class);
		//save the employee using the emplotee paramaters
		//if id==0 then it will insert/save or else it will update
		currentSession.saveOrUpdate(theEmployee);
					
		
	}
	@Override
	public void deleteById(int theId) {
		//get current hibernate session
		Session currentSession=entityManager.unwrap(Session.class);
		//delete the employee with id
		//currentSession.createQuery("delete from Employee where id=: " + theId).executeUpdate();
		javax.persistence.Query theQuery=currentSession.createQuery("delete from Employee where id=:EmployeeId");
		theQuery.setParameter("EmployeeId", theId);
		theQuery.executeUpdate();
		
	}

	

}

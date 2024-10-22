package com.wipro.bankofamerica.estore.service;

import java.util.List;

import com.wipro.bankofamerica.estore.model.Employee;

public interface EmployeeService
{

	public Employee saveEmployee(Employee employee);
	public Employee getEmployee(Integer id);
	void deleteEmployee(Integer id);
	
	
	 List<Employee> getAllEmployees();
	 Employee updateEmployee(Integer id, Employee employee);
	 Employee getEmployeeByEmail(String emailId); 

}

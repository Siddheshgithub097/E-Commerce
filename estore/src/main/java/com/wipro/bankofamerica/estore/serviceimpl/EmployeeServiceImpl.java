package com.wipro.bankofamerica.estore.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bankofamerica.estore.exception.EmployeeNotFoundException;
import com.wipro.bankofamerica.estore.model.Employee;
import com.wipro.bankofamerica.estore.repository.EmployeeRepository;
import com.wipro.bankofamerica.estore.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	private EmployeeRepository employeeRepository ;

	@Override
	public Employee saveEmployee(Employee employee) 
	{
		Optional<Employee> existingemployee = employeeRepository.findByEmailId(employee.getEmailId());
		
		if(existingemployee.isPresent())
		{
			throw new RuntimeException("Employee with the email ID " + employee.getEmailId() + " already exists.");
		}
				
		return employeeRepository.save(employee);
			
	}

	@Override
	public Employee getEmployee(Integer id) 
	{
		
		return employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found."));
		
	}

	@Override
	public void deleteEmployee(Integer id) 
	{
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found.") );
		
		
		 employeeRepository.delete(employee);
		
		
		/* Optional<Employee> optional = employeeRepository.findById(id);
		
		if(optional.isPresent())
		{
			
			employeeRepository.delete(optional.get());
		}
		else {
	        throw new RuntimeException("Employee with ID " + id + " not found.");
	    }
	    */
		
	}
	
	

	@Override
	public List<Employee> getAllEmployees() 
	{
		List<Employee> employees = employeeRepository.findAll();
		
		if(employees.isEmpty())
		{
			throw new EmployeeNotFoundException(" Employee Not Found.");
		}
		return employees;
	}

	@Override
	public Employee updateEmployee(Integer id, Employee employee)
	{
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found."));
		
		// i want to update 
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmailId(employee.getEmailId());
		existingEmployee.setCity(employee.getCity());
		existingEmployee.setAddresses(employee.getAddresses());
		
		return employeeRepository.save(existingEmployee);
	}

	@Override
	public Employee getEmployeeByEmail(String emailId) 
	{
		Employee employee = employeeRepository.findByEmailId(emailId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with EmailId " + emailId + " not found."));
		
		return employee;
	}

}

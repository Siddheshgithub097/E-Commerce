package com.wipro.bankofamerica.estore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bankofamerica.estore.model.Employee;
import com.wipro.bankofamerica.estore.service.EmployeeService;
import com.wipro.bankofamerica.estore.util.ResponseStructure;

@RestController
@RequestMapping("/employee")
public class EmployeeController 
{
	@Autowired
	private EmployeeService employeeService ;

	@PostMapping("/")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee employee)
	{
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Employee saved successfully");
		structure.setData(employeeService.saveEmployee(employee));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Employee>> getEmployee(@PathVariable Integer id)
	{
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Employee retrieved successfully");
		structure.setData(employeeService.getEmployee(id));
		
		return ResponseEntity.ok(structure);
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteEmployee(@PathVariable Integer id)
	{
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		employeeService.deleteEmployee(id);
		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("Employee deleted successfully");
		structure.setData("Employee with ID: " + id + " has been deleted.");

        return ResponseEntity.ok(structure);
		
	}
	
	
	 @GetMapping("/")
	    public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmployees() {
	        ResponseStructure<List<Employee>> structure = new ResponseStructure<>();

	        structure.setStatus(HttpStatus.OK.value());
	        structure.setMessage("All employees retrieved successfully");
	        structure.setData(employeeService.getAllEmployees());

	        return ResponseEntity.ok(structure);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
	        ResponseStructure<Employee> structure = new ResponseStructure<>();

	        structure.setStatus(HttpStatus.OK.value());
	        structure.setMessage("Employee updated successfully");
	        structure.setData(employeeService.updateEmployee(id, employee));

	        return ResponseEntity.ok(structure);
	    }

	    @GetMapping("/email/{emailId}")
	    public ResponseEntity<ResponseStructure<Employee>> getEmployeeByEmail(@PathVariable String emailId) {
	        ResponseStructure<Employee> structure = new ResponseStructure<>();

	        structure.setStatus(HttpStatus.OK.value());
	        structure.setMessage("Employee retrieved successfully");
	        structure.setData(employeeService.getEmployeeByEmail(emailId));

	        return ResponseEntity.ok(structure);
	   
	    }
}

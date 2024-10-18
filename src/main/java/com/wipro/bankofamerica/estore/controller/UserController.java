package com.wipro.bankofamerica.estore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.bankofamerica.estore.exception.InvalidCredentialsException;
import com.wipro.bankofamerica.estore.exception.UserAlreadyExistsException;
import com.wipro.bankofamerica.estore.exception.UserNotFoundException;
import com.wipro.bankofamerica.estore.model.User;
import com.wipro.bankofamerica.estore.service.UserService;
import com.wipro.bankofamerica.estore.util.ResponseStructure;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserService userService ;
	
	@PostMapping("/login")
    public ResponseEntity<ResponseStructure<User>> loginUser(@RequestParam String username, @RequestParam String password) {
        ResponseStructure<User> response = new ResponseStructure<>();
        try {
            User user = userService.loginUser(username, password);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Login successful");
            response.setData(user);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException | InvalidCredentialsException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
	
	@PostMapping("/register")
    public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
        ResponseStructure<User> response = new ResponseStructure<>();
        try {
            User savedUser = userService.saveUser(user);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("User registered successfully");
            response.setData(savedUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UserAlreadyExistsException e) {
            response.setStatus(HttpStatus.CONFLICT.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<List<User>>> getAllUsers() {
	    System.out.println("Invoked getAllUsers method");
	    ResponseStructure<List<User>> response = new ResponseStructure<>();
	    try {
	        List<User> users = userService.getAllUser();
	        if (users.isEmpty()) {
	            response.setStatus(HttpStatus.OK.value());
	            response.setMessage("No users found");
	        } else {
	            response.setStatus(HttpStatus.OK.value());
	            response.setMessage("Users retrieved successfully");
	        }
	        response.setData(users);
	        return ResponseEntity.ok(response);
	    } catch (UserNotFoundException e) {
	        response.setStatus(HttpStatus.NO_CONTENT.value());
	        response.setMessage(e.getMessage());
	        response.setData(null);
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	    } catch (Exception e) {
	        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        response.setMessage("An error occurred: " + e.getMessage());
	        response.setData(null);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
	
	@GetMapping("/city/{city}")
    public ResponseEntity<ResponseStructure<List<User>>> getUsersByCity(@PathVariable String city) {
        ResponseStructure<List<User>> response = new ResponseStructure<>();
        try {
            List<User> users = userService.getListByCity(city);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Users in " + city + " retrieved successfully");
            response.setData(users);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
    }
	
	@GetMapping("/{username}")
    public ResponseEntity<ResponseStructure<User>> getUserByUsername(@PathVariable String username) {
        ResponseStructure<User> response = new ResponseStructure<>();
        try {
            User user = userService.getUserByUserName(username);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("User retrieved successfully");
            response.setData(user);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage(e.getMessage());
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
	

}

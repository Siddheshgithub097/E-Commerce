package com.wipro.bankofamerica.estore.controller;

import java.util.List;

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

import com.wipro.bankofamerica.estore.model.User;
import com.wipro.bankofamerica.estore.service.UserService;
import com.wipro.bankofamerica.estore.util.ResponseStructure;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<User>> loginUser(@RequestParam String username, @RequestParam String password) {
        ResponseStructure<User> response = new ResponseStructure<>();
        User user = userService.loginUser(username, password);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Login successful");
        response.setData(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        ResponseStructure<User> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("User registered successfully");
        response.setData(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseStructure<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUser();
        ResponseStructure<List<User>> response = new ResponseStructure<>();
        if (users.isEmpty()) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("No users found");
        } else {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Users retrieved successfully");
        }
        response.setData(users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<ResponseStructure<List<User>>> getUsersByCity(@PathVariable String city) {
        List<User> users = userService.getListByCity(city);
        ResponseStructure<List<User>> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Users in " + city + " retrieved successfully");
        response.setData(users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{username}")
    public ResponseEntity<ResponseStructure<User>> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUserName(username);
        ResponseStructure<User> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("User retrieved successfully");
        response.setData(user);
        return ResponseEntity.ok(response);
    }
}

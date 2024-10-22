package com.wipro.bankofamerica.estore.service;

import java.util.List;

import com.wipro.bankofamerica.estore.model.User;

public interface UserService
{

	public User loginUser(String username, String password);
	public User saveUser(User user);
	public List<User> getListByCity(String city);
	public User getUserByUserName(String username);
	List<User> getAllUser();

}

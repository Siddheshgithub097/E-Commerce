package com.wipro.bankofamerica.estore.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.bankofamerica.estore.exception.InvalidCredentialsException;
import com.wipro.bankofamerica.estore.exception.UserAlreadyExistsException;
import com.wipro.bankofamerica.estore.exception.UserNotFoundException;
import com.wipro.bankofamerica.estore.model.User;
import com.wipro.bankofamerica.estore.repository.UserRepository;
import com.wipro.bankofamerica.estore.service.UserService;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserRepository userRepository ;


	@Override
	public User loginUser(String username, String password)
	{
		User user = userRepository.findByUsername(username);

		if(user == null )
		{
			throw new UserNotFoundException("User with username: " + username + " not found.");
		}

		if(!password.equals(user.getPassword()))
		{
			throw new InvalidCredentialsException("Invalid username or password");
		}

		return user;
	}



	@Override
	public User saveUser(User user)
	{
		if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("User with username: " + user.getUsername() + " already exists.");
        }

		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser()
	{

		List<User> users = userRepository.findAll();
		if(users.isEmpty())
		{
			 throw new UserNotFoundException("No users found");
		}
		return users;

	}

	@Override
	public List<User> getListByCity(String city)
	{
		List<User> userslist = userRepository.findByCity(city);
		if(userslist.isEmpty())
		{
			throw new UserNotFoundException("No users found in city: " + city);
		}

		return userslist;
	}

	@Override
	public User getUserByUserName(String username)
	{
		User user = userRepository.findByUsername(username);

		if (user == null)
        {
            throw new UserNotFoundException("User with username: " + username + " not found.");
        }

        return user;
	}



}

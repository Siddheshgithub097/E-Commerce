package com.wipro.bankofamerica.estore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.bankofamerica.estore.model.User;

public interface UserRepository  extends JpaRepository<User, Long>
{

	User findByUsername(String username);

	List<User> findByCity(String city);


}

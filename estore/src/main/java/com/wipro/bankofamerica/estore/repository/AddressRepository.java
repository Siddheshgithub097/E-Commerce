package com.wipro.bankofamerica.estore.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.bankofamerica.estore.model.Addresses;

public interface AddressRepository extends JpaRepository<Addresses, Integer>  {

	Address save(Address address);

	List<Addresses> findByCity(String city);

}

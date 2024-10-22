package com.wipro.bankofamerica.estore.service;

import java.util.List;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import com.wipro.bankofamerica.estore.model.Addresses;

public interface AddressService 
{

	public Addresses saveAddress(Addresses address);
	
	public Addresses getAddress(Integer id);
    public void deleteAddress(Integer id);
    Addresses updateAddress(Integer id, Addresses address);
    List<Addresses> getAddressesByCity(String city);
}

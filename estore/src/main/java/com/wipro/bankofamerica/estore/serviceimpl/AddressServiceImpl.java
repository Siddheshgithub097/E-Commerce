package com.wipro.bankofamerica.estore.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.stereotype.Service;

import com.wipro.bankofamerica.estore.model.Addresses;
import com.wipro.bankofamerica.estore.repository.AddressRepository;
import com.wipro.bankofamerica.estore.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository ;
	

	@Override
	public Addresses saveAddress(Addresses address) {
		
		return addressRepository.save(address);
	}
	
	@Override
    public Addresses getAddress(Integer id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address with ID " + id + " not found."));
    }


	@Override
	public void deleteAddress(Integer id) {
	   
	    if (!addressRepository.existsById(id)) 
	    {
	        throw new RuntimeException("Address with ID " + id + " not found.");
	    }
	    
	    addressRepository.deleteById(id);
	}

	@Override
	public Addresses updateAddress(Integer id, Addresses address) {
	   
	    Addresses existingAddress = addressRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Address with ID " + id + " not found."));
	    
	    
	    existingAddress.setStreet(address.getStreet());
	    existingAddress.setCity(address.getCity());
	    existingAddress.setState(address.getState());
	    existingAddress.setZip(address.getZip());
	    
	   
	    return addressRepository.save(existingAddress);
	}

	@Override
    public List<Addresses> getAddressesByCity(String city) {
        return addressRepository.findByCity(city); 
    }

	

}

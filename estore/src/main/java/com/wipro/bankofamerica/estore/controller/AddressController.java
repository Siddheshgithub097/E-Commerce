package com.wipro.bankofamerica.estore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.bankofamerica.estore.model.Addresses;
import com.wipro.bankofamerica.estore.service.AddressService;
import com.wipro.bankofamerica.estore.util.ResponseStructure;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

   
    @PostMapping("/")
    public ResponseEntity<ResponseStructure<Addresses>> saveAddress(@RequestBody Addresses address) {
        ResponseStructure<Addresses> structure = new ResponseStructure<>();
        structure.setStatus(HttpStatus.CREATED.value());
        structure.setMessage("Address saved successfully");
        structure.setData(addressService.saveAddress(address)); 

        return ResponseEntity.status(HttpStatus.CREATED).body(structure);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Addresses>> getAddress(@PathVariable Integer id) {
        ResponseStructure<Addresses> structure = new ResponseStructure<>();
        structure.setStatus(HttpStatus.OK.value());
        structure.setMessage("Address retrieved successfully");
        structure.setData(addressService.getAddress(id));

        return ResponseEntity.ok(structure);
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Addresses>> updateAddress(@PathVariable Integer id,
                                                                    @RequestBody Addresses address) {
        ResponseStructure<Addresses> structure = new ResponseStructure<>();
        structure.setStatus(HttpStatus.OK.value());
        structure.setMessage("Address updated successfully");
        structure.setData(addressService.updateAddress(id, address));

        return ResponseEntity.ok(structure);
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);

        ResponseStructure<String> structure = new ResponseStructure<>();
        structure.setStatus(HttpStatus.OK.value());
        structure.setMessage("Address deleted successfully");
        structure.setData("Address with ID: " + id + " has been deleted.");

        return ResponseEntity.ok(structure);
    }

   
    @GetMapping("/city/{city}")
    public ResponseEntity<ResponseStructure<List<Addresses>>> getAddressesByCity(@PathVariable String city) {
        ResponseStructure<List<Addresses>> structure = new ResponseStructure<>();
        structure.setStatus(HttpStatus.OK.value());
        structure.setMessage("Addresses retrieved successfully");
        structure.setData(addressService.getAddressesByCity(city));

        return ResponseEntity.ok(structure);
    }
}
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

import com.wipro.bankofamerica.estore.model.Product;
import com.wipro.bankofamerica.estore.service.ProductService;
import com.wipro.bankofamerica.estore.util.ResponseStructure; // Import your ResponseStructure class

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
   public ResponseEntity<ResponseStructure<Iterable<Product>>> getAllProducts()
   {
	   ResponseStructure<Iterable<Product>> structure = new ResponseStructure<>();

	   structure.setStatus(HttpStatus.OK.value());
	   structure.setMessage("Products retrieved successfully");
	   structure.setData(productService.getAllProduct());

	   return  ResponseEntity.ok(structure);
   }

    @PostMapping("/")
   public ResponseEntity<ResponseStructure<Product>> saveProducts(@RequestBody Product product)
   {
	   ResponseStructure<Product> structure = new ResponseStructure<>();

	   structure.setStatus(HttpStatus.CREATED.value());
	   structure.setMessage("Product saved successfully");
	   structure.setData(productService.saveProduct(product));

	  return ResponseEntity.status(HttpStatus.CREATED).body(structure);

   }

    @GetMapping("/{id}")
   public ResponseEntity<ResponseStructure<Product>> getProductById(@PathVariable Integer id)
   {
	   ResponseStructure<Product> structure = new ResponseStructure<>();

	   structure.setStatus(HttpStatus.OK.value());
	   structure.setMessage("Product retrieved successfully");
	   structure.setData(productService.getProductById(id));

	   return ResponseEntity.ok(structure);
   }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Product>> updateProduct( @PathVariable Integer id,@RequestBody Product product)
    {
        ResponseStructure<Product> structure = new ResponseStructure<>();

        structure.setStatus(HttpStatus.OK.value());
        structure.setMessage("Product updated successfully");
        structure.setData(productService.updateProduct(id, product));

        return ResponseEntity.ok(structure);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable Integer id)
    {
    	ResponseStructure<String> structure = new ResponseStructure<>();

    	productService.deleteProduct(id);

    	structure.setStatus(HttpStatus.OK.value());
    	structure.setMessage("Product deleted successfully");
    	structure.setData("Product with ID: " + id + " has been deleted.");

    	return ResponseEntity.ok(structure);
    }


    @PostMapping("/savebulk")
    public ResponseEntity<ResponseStructure<List<Product>>> saveMultipleProducts(@RequestBody List<Product> products)
    {
    	ResponseStructure<List<Product>> structure = new ResponseStructure<>();

    	structure.setStatus(HttpStatus.CREATED.value());
    	structure.setMessage(" All Product saved successfully");
    	structure.setData(productService.saveMultipleProducts(products));

    	return ResponseEntity.status(HttpStatus.CREATED).body(structure);
    }



  }

package com.wipro.bankofamerica.estore.service;

import java.util.List;

import com.wipro.bankofamerica.estore.model.Product;

public interface ProductService 
{

	public Iterable<Product> getAllProduct();
	public Product saveProduct(Product product);
	public <optional>Product getProductById(Integer Id);
	public void deleteProduct(Integer id);
	public List<Product> saveMultipleProducts(List<Product> products);
	public Product updateProduct(Integer id, Product product);

}

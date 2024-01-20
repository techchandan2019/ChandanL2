package com.neosoft.service;

import java.util.List;

import com.neosoft.model.Product;

public interface IService {
	
	public String saveProduct(Product prod);
	public List<Product> fetchAllProduct();
	public String fullUpdate(Product prod);
	public String partialUpdate(Double price,Integer id);
	public String deleteProduct(Integer id);

}

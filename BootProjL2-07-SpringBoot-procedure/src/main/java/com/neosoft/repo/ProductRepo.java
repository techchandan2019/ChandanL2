package com.neosoft.repo;

import org.springframework.data.repository.CrudRepository;

import com.neosoft.model.Product;

public interface ProductRepo extends CrudRepository<Product, Integer> {

	
}

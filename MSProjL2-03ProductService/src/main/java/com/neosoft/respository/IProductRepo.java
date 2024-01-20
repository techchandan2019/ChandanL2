package com.neosoft.respository;

import org.springframework.data.repository.CrudRepository;

import com.neosoft.model.Product;

public interface IProductRepo extends CrudRepository<Product, Integer> {

}

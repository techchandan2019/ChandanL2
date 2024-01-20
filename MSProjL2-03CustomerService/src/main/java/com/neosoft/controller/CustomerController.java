package com.neosoft.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.feignservice.IProductService;
import com.neosoft.model.Product;

@RestController
public class CustomerController {
	
	private static final Logger logger=LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private IProductService service;

	@GetMapping("/getcustMsg")
	public ResponseEntity<?> getMessage(){
		logger.info("controller class of customer-service");
		return new ResponseEntity<String>(service.getMessage(),HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveProductDetails(@RequestBody Product prod){
		logger.info("save product method of Customer service");
		return service.saveProduct(prod);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProductsDetails(){
		logger.info("get All product details of Customerservice");
		return service.getAllProduct();
	}
	
	@PutMapping("/updateFull")
	public ResponseEntity<String> updateFullProductDetails(@RequestBody Product prod){
		logger.info("update full product details of Customerservice");
		return service.updateFullProduct(prod);
	}
	
	@PatchMapping("/updatePartial/{price}/{id}")
	public ResponseEntity<String> updatePartialProductDetails(@PathVariable Double price,@PathVariable Integer id){
		logger.info("update partial product details of Customerservice");
		return service.updatePartialProduct(price, id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteByIdProduct(@PathVariable Integer id){
		logger.info("delete product details of Customerservice");
		return service.deleteByIdProduct(id);
	}
}

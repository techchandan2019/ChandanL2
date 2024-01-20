package com.neosoft.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.model.Product;
import com.neosoft.service.IService;

@RestController
public class ProductController {
	
	private static final Logger logger =LoggerFactory.getLogger(ProductController.class);
	
	@Value("${db.username}")
	private String dbuser;
	@Value("${db.password}")
	private String dbpwd;
	
	@Autowired
	private IService service;

	@GetMapping("/getMsg")
	public ResponseEntity<?> getMessage(){
		logger.info("controller of Product-Service");
		return new ResponseEntity<String>("Config username and password "+dbuser+" "+dbpwd,HttpStatus.OK);
	}
	@PostMapping("/save")
	public ResponseEntity<String> saveProduct(@RequestBody Product prod){
		logger.info("save product method of Product service");
		return new ResponseEntity<String>(service.saveProduct(prod),HttpStatus.OK);
	}
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllProduct(){
		logger.info("getAll  product method of Product service");
		List<Product> li=service.fetchAllProduct();
		
		return new ResponseEntity<List<Product>>(li,HttpStatus.OK);
	}
	@PutMapping("/updateFull")
	public ResponseEntity<?> updateFullProduct(@RequestBody Product prod){
		logger.info("full update product method of Product service");
		return new ResponseEntity<String> (service.fullUpdate(prod),HttpStatus.OK);
	}
	@GetMapping("/updatePartial/{price}/{id}")
	public ResponseEntity<?> updatePartialProduct(@PathVariable Double price,@PathVariable Integer id){
		logger.info("partial update product method of Product service");
		return new ResponseEntity<String> (service.partialUpdate(price, id),HttpStatus.OK);
	}
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteByIdProduct(@PathVariable Integer id){
		logger.info("delete product method of Product service");
		String msg=service.deleteProduct(id);
		return new ResponseEntity<String> (msg,HttpStatus.OK);
	}
}

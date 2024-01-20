package com.neosoft.feignservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.neosoft.model.Product;

@FeignClient("product-service")
public interface IProductService {
	
	@GetMapping("/getMsg")
	public String getMessage();
	
	@PostMapping("/save")
	public ResponseEntity<String> saveProduct(@RequestBody Product prod);

	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProduct();
	
	@PutMapping("/updateFull")
	public ResponseEntity<String> updateFullProduct(@RequestBody Product prod);
	
	@GetMapping("/updatePartial/{price}/{id}")
	public ResponseEntity<String> updatePartialProduct(@PathVariable Double price,@PathVariable Integer id);
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteByIdProduct(@PathVariable Integer id);
}

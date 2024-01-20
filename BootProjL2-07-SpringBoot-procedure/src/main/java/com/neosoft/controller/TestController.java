package com.neosoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.model.Customer;
import com.neosoft.model.Employee;
import com.neosoft.repo.NamePrice;
import com.neosoft.service.TestService;

@RestController
public class TestController {

	@Autowired
	private TestService service;
	@GetMapping("/getEmp/{dept}")
	public List<Employee> getEmp(@PathVariable Integer dept) {
		return service.getEmployeeByDept(dept);
		
	}
	@GetMapping("/getEmpById/{id}")
	public List<Employee> getEmpById(@PathVariable Integer id) {
		return service.getEmpById(id);
		
	}
	@PostMapping("/save")
	public String saveCustomer(@RequestBody Customer cust) {
		return service.saveCustomer(cust);
		
	}
	@GetMapping("/getCustProd")
	public List<NamePrice> getCustProddetails() {
//		return service.getCustomerAndProduct();
		return service.getCustomerAndProductJoin();
		
	}
	@GetMapping("/getCustProd1")
	public List<Customer> getCustProdDetailsLeftJoin() {
//		return service.getCustomerAndProduct();
		return service.getCustomerAndProductLeftJoin();
		
	}
	
}

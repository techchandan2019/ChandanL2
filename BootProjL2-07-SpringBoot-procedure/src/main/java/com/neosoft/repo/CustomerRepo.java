package com.neosoft.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.neosoft.model.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {

	
	@Query("Select new com.neosoft.repo.NamePrice(c.name,p.price) from Customer c JOIN c.products p")
	public List<NamePrice> getEmpProdNameAndPrice();
	
//	@Query("select c from Customer c LEFT JOIN c.products p ")
//	@Query("select c from Customer c RIGHT JOIN c.products p ")
	@Query("select c from Customer c JOIN c.products p ")
	public List<Customer> getCustomerAndProductLeftJoin();
	
}

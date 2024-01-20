package com.neosoft.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.model.Customer;
import com.neosoft.model.Employee;
import com.neosoft.repo.CustomerRepo;
import com.neosoft.repo.NamePrice;

@Service
public class TestService {
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private CustomerRepo custRepo;
	
	public List<Employee> getEmployeeByDept(Integer dept){
		StoredProcedureQuery query=entityManager.createStoredProcedureQuery("P_GET_EMP",Employee.class);
		query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2, Employee.class, ParameterMode.REF_CURSOR);
		
		query.setParameter(1, dept);
		List<Employee> li=query.getResultList();
		return li;
	}
	
	public List<Employee> getEmpById(Integer id){
		TypedQuery<Employee> createQuery = entityManager.createQuery("from Employee where eno=?1",Employee.class);
		createQuery.setParameter(1, id);
		
		List<Employee> resultList = createQuery.getResultList();
		return resultList;
		
	}
	public String saveCustomer(Customer cust) {
		custRepo.save(cust);
		return "customer save successfully";
	}
	public List<NamePrice> getCustomerAndProduct(){
//		Query createQuery = entityManager.createQuery("Select c.name,p.price from Customer c Join c.Product p");
//		List<Object[]> resultList = createQuery.getResultList();
//		return resultList;
//		
		return custRepo.getEmpProdNameAndPrice();
	}
	public List<NamePrice> getCustomerAndProductJoin(){
		Query createQuery = entityManager.createQuery("Select new com.neosoft.repo.NamePrice(c.name,p.price) from Customer c Join c.products p");
		List<NamePrice> resultList = createQuery.getResultList();
		return resultList;
	}
	public List<Customer> getCustomerAndProductLeftJoin(){
		
		return custRepo.getCustomerAndProductLeftJoin();
	}
}

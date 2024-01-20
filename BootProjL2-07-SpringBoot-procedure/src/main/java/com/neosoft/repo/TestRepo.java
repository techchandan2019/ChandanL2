package com.neosoft.repo;

import org.springframework.data.repository.CrudRepository;

import com.neosoft.model.Employee;

public interface TestRepo extends CrudRepository<Employee, Integer> {

	
}

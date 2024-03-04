package com.bonami.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonami.model.EmpRequestDTO;
import com.bonami.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TestController {

	@QueryMapping("getEmp")
	public Employee getEmployee(@Argument Integer empId) {

		return new Employee(empId,"chandan","software");
	}
	@QueryMapping("getEmpList")
	public List<Employee> getEmployeeList() {
		List<Employee> li=new ArrayList<>();
		li.add(new Employee(1,"chandan","software"));
		li.add(new Employee(2,"ckp","software"));
		return li;
	}
	@MutationMapping("addEmp")
	public Employee saveEmployee(@Argument EmpRequestDTO empRequestDTO) {

		
		//both are possible
		return new Employee(1,empRequestDTO.getName(),empRequestDTO.getDept());
//	return Mono.just(new Employee(1,empRequestDTO.getName(),empRequestDTO.getDept()));
	}

	
}

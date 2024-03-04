package com.bonami.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.bonami.model.EmpRequestDTO;
import com.bonami.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;

@RestController
public class ConsumerController {

	@GetMapping("/emp")
	public Employee getEmp() {
		ObjectMapper objectMapper = new ObjectMapper();

		WebClient webClient = WebClient.builder()
		.baseUrl("http://localhost:4041/graphql")//url of graphql instance
		.build();
		
		GraphQLWebClient graphqlClient = GraphQLWebClient.newInstance(webClient, objectMapper);
		Employee emp=graphqlClient.post("Query1.graphql", 
				Map.of("empId", 1), Employee.class)
				            .block();   
		return emp;
	}
	@PostMapping("/saveEmp")
	public Employee saveEmployee(@RequestBody EmpRequestDTO empRequestDTO) {
		ObjectMapper objectMapper = new ObjectMapper();

		WebClient webClient = WebClient.builder()
		.baseUrl("http://localhost:4041/graphql")//url of graphql instance
		.build();
		
		GraphQLWebClient graphqlClient = GraphQLWebClient.newInstance(webClient, objectMapper);
		Employee emp=graphqlClient.post("Query2.graphql", 
				Map.of("empRequestDTO", empRequestDTO), Employee.class)
				            .block();   
		return emp;
	}
	
}

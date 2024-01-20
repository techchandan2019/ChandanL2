package com.neosoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.model.Student;
import com.neosoft.repo.IStudentRepo;

@RestController
public class StudentController {

	@Autowired
	private IStudentRepo repo;
	
	@PostMapping("/save")
	public Student saveMethod(@RequestBody Student stud){
		return repo.save(stud);
	}
	@GetMapping("/getStud/{id}")
	public Student getStudent(@PathVariable Integer id) {
		return repo.findById(id).get();
	}
}

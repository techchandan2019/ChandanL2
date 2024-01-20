package com.neosoft.repo;

import org.springframework.data.repository.CrudRepository;

import com.neosoft.model.Student;

public interface IStudentRepo extends CrudRepository<Student, Integer>{

}

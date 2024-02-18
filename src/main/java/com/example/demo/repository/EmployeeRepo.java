package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Employee;

public interface EmployeeRepo extends CrudRepository<Employee,Long>,JpaRepository<Employee,Long> {
	
	
 boolean existsByEmail(String email);
	Employee findByEmail(String email);

}

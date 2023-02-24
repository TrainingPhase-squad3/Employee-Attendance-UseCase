package com.squad3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.squad3.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	Employee findByEmail(String email);

	

}

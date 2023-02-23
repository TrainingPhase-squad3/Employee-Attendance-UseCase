package com.squad3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.squad3.entity.EmployeeAttendance;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long>{
	
}

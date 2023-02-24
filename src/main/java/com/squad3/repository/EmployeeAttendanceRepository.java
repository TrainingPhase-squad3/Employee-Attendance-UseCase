package com.squad3.repository;

import java.time.LocalDate;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.squad3.entity.EmployeeAttendance;


public interface EmployeeAttendanceRepository  {

	
EmployeeAttendance findByEmployee_EmployeeIdAndDate(long id, LocalDate date);
List<EmployeeAttendance> findByEmployee_EmployeeIdAndDateBetween(long empId,LocalDate fromDate, LocalDate toDate);


}

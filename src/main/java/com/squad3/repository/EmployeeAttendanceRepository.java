package com.squad3.repository;

import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.squad3.entity.Employee;
import com.squad3.entity.EmployeeAttendance;

@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {

	EmployeeAttendance findByEmployee_EmployeeIdAndDate(long id, LocalDate date);

	List<EmployeeAttendance> findByEmployee_EmployeeIdAndDateBetween(long empId, LocalDate fromDate, LocalDate toDate);

}

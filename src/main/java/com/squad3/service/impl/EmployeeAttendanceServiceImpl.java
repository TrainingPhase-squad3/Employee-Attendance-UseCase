package com.squad3.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squad3.entity.Employee;
import com.squad3.entity.EmployeeAttendance;
import com.squad3.exception.EmployeeNotFoundException;
import com.squad3.repository.EmployeeAttendanceRepository;
import com.squad3.repository.EmployeeRepository;
import com.squad3.service.EmployeeAttendanceService;

@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeAttendanceRepository employeeAttendanceRepository;

	Logger logger = LoggerFactory.getLogger(EmployeeAttendanceServiceImpl.class);

	@Override
	public EmployeeAttendance swipping(long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with Id: " + employeeId + " not found"));
		EmployeeAttendance employeeAttendance = employeeAttendanceRepository
				.findByEmployee_EmployeeIdAndDate(employeeId, LocalDate.now());
		if (employeeAttendance == null) {
			EmployeeAttendance attendance = new EmployeeAttendance();
			attendance.setSwipeInTime(LocalTime.now());
			attendance.setDate(LocalDate.now());
			attendance.setEmployee(employee);
			logger.info("Employee Swipped In");
			return employeeAttendanceRepository.save(attendance);

		}
		logger.info("Employee Swipped Out");
		employeeAttendance.setSwipeOutTime(LocalTime.now());
		return employeeAttendanceRepository.save(employeeAttendance);

	}
	

}

package com.squad3.service.impl;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;


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
	public List<SwipingResponse> employeeAttendenceHistory(long adminId, long empId, String fromDate, String toDate) {
		Employee employee = employeeRepository.findById(adminId)
				.orElseThrow(() -> new EmployeeNotFoundException("Admin:" + adminId + " not found"));
		if (employee.getRole().equals("Admin")) {
			logger.info("Employee is Admin");
			if (employee.isStatus()) {
				logger.info("Admin logged in");
				Employee employee1 = employeeRepository.findById(empId)
						.orElseThrow(() -> new EmployeeNotFoundException("Employee:" + empId + " not found"));

				List<EmployeeAttendance> list = employeeAttendanceRepository.findByEmployee_EmployeeIdAndDateBetween(
						employee1.getEmployeeId(), LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")),
						LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
				if (list.isEmpty()) {
					logger.warn("empty list due to no attendance history for employee");
					throw new NotImplementedException("No swiping history in the specified dates");

				} else {
					return list.stream().map(history -> {
						return new SwipingResponse(history.getDate(), history.getSwipeInTime(),
								history.getSwipeOutTime());
					}).toList();
				}

			} else {
				logger.error("Admin not logged in");
				throw new NotImplementedException("Not logged in");
			}

		} else {
			logger.error("unauthorized employee");
			throw new AccessDeniedException("Unauthorized access");

		}
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

package com.squad3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.squad3.dto.EmployeeDto;
import com.squad3.entity.EmployeeAttendance;
import com.squad3.response.Response;
import com.squad3.service.EmployeeAttendanceService;
import com.squad3.service.impl.EmployeeServiceImpl;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;
	
	@Autowired
	EmployeeServiceImpl employeeServiceImpl;
	
	@PostMapping
	public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeeDto employeedto) {
		Response employee = employeeServiceImpl.save(employeedto);
		return ResponseEntity.status(HttpStatus.CREATED).body(employee.getMessage());
	}
	@PostMapping("/employee-attendance")
	public ResponseEntity<EmployeeAttendance> swipping(@Valid @RequestParam long employeeId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeAttendanceService.swipping(employeeId));
	}


}

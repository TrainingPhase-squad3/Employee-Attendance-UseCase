package com.squad3.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.squad3.response.SwipingResponse;
import com.squad3.service.impl.EmployeeAttendanceServiceImpl;
import com.squad3.service.impl.EmployeeServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	EmployeeAttendanceServiceImpl attendanceServiceImpl;
	@Autowired
	EmployeeServiceImpl employeeServiceImpl;

	@PostMapping(value = "/login")
	public ResponseEntity<ResponseStructure> adminLogIn(@Valid @RequestBody LoginDto dto) {

		return new ResponseEntity<>(employeeServiceImpl.adminLogIn(dto.getEmail(), dto.getPassword()), HttpStatus.OK);

	}

	@PutMapping(value = "/logout")
	public ResponseEntity<ResponseStructure> adminLogOut(@RequestParam String email) {
		return new ResponseEntity<>(employeeServiceImpl.adminLogOut(email), HttpStatus.OK);

	}


	@GetMapping("/employee-attendence")
	public List<SwipingResponse> employeeAttendanceHistory(@Valid @RequestParam long adminId, long employeeId,
			String fromDate, String toDate) {
		return attendanceServiceImpl.employeeAttendenceHistory(adminId, employeeId, fromDate, toDate);

	}

	
}

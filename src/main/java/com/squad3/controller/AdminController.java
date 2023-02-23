package com.squad3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.squad3.dto.LoginDto;
import com.squad3.response.ResponseStructure;
import com.squad3.service.impl.EmployeeServiceImpl;

@RequestMapping("/admin")
public class AdminController {

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

}

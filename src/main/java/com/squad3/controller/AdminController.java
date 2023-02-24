package com.squad3.controller;

import org.springframework.web.bind.annotation.RequestMapping;

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

package com.squad3.service.impl;

import org.springframework.stereotype.Service;

import com.squad3.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {


	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Response save(EmployeeDto employeedto) throws EmployeeAlreadyExists {
		Employee employee = employeeRepository.findByEmail(employeedto.getEmail());
		if (employee != null) {
			if (employeedto.getPassword().equals(employee.getPassword()))
				return new Response("Employee Already Registered");
			return new Response("Incorrect  password");

		}

		employeeRepository.save(Employee.builder().employeeId((long) (Math.random() * (999999 - 100000) + 1) + 10000)
				.name(employeedto.getName()).role(employeedto.getRole()).password(employeedto.getPassword())
				.email(employeedto.getEmail()).build());
		return new Response("Employee Registered successfully");
	}
}

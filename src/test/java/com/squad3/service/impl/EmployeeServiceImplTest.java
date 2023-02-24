package com.squad3.service.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {


@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Mock
	EmployeeRepository employeeRepository;
	
	
	@Test
	void testsaveEmployee() {
		EmployeeDto employeeDto = new EmployeeDto("chaitra", "Admin", "zxoZW%1we", "gcs@gmail.com");
		Mockito.when(employeeRepository.findByEmail(employeeDto.getEmail())).thenReturn(null);
		Mockito.when(employeeRepository.save(Employee.builder().employeeId((long) (Math.random() * Math.pow(6, 7)))
				.name(employeeDto.getName()).role(employeeDto.getRole()).password(employeeDto.getPassword())
				.email(employeeDto.getEmail()).build())).thenReturn(new Employee());
		assertEquals(employeeServiceImpl.save(employeeDto).getMessage(),
				new Response("Employee Registered successfully").getMessage());
	}

	@Test
	void testsaveEmployeeLoggin() {
		EmployeeDto employeeDto = new EmployeeDto("chaitra", "Admin", "zxoZW%1we", "gcs@gmail.com");

		Employee employee = new Employee(247789, "chaitra", "Admin", "gcs@gmail.com", "zxoZW%1", LocalDate.now(),
				false);
		Mockito.when(employeeRepository.findByEmail(employeeDto.getEmail())).thenReturn(employee);

		assertEquals(employeeServiceImpl.save(employeeDto).getMessage(),
				new Response("Incorrect  password").getMessage());

	}


}

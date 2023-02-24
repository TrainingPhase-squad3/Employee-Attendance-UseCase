package com.squad3.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.squad3.dto.EmployeeDto;
import com.squad3.entity.Employee;
import com.squad3.exception.InvalidEmployeeException;
import com.squad3.repository.EmployeeRepository;
import com.squad3.response.Response;
import com.squad3.response.ResponseStructure;

@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {
	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Mock
	EmployeeRepository employeeRepository;

	@Test
	void adminLogInTest() {
		String email = "darshu@gmail.com";
		String password = "Darsh@19";

		Employee employee = new Employee();
		employee.setEmployeeId(123456);
		employee.setName("darshan");
		employee.setEmail("darshu@gmail.com");
		employee.setPassword("Darsh@19");
		employee.setRole("Admin");
		employee.setDoj(LocalDate.parse("2023-05-19"));
		employee.setStatus(false);

		Mockito.when((employeeRepository.findByEmail(email))).thenReturn(employee);
		ResponseStructure result = employeeServiceImpl.adminLogIn(email, password);
		assertNotNull(result);
		assertEquals("Logged In successfully", result.getMessage());

	}

	@Test
	void adminLogInTestToCheckAlreadyLogin() {
		String email = "darshu@gmail.com";
		String password = "Darsh@19";

		Employee employee = new Employee();
		employee.setEmployeeId(123456);
		employee.setName("darshan");
		employee.setEmail("darshu@gmail.com");
		employee.setPassword("Darsh@19");
		employee.setRole("Admin");
		employee.setDoj(LocalDate.parse("2023-05-19"));
		employee.setStatus(true);

		Mockito.when((employeeRepository.findByEmail(email))).thenReturn(employee);
		ResponseStructure result = employeeServiceImpl.adminLogIn(email, password);
		assertNotNull(result);
		assertEquals(" admin already Logged In ", result.getMessage());

	}

	@Test
	void adminLogInTestToCheckAUthentication() {
		String email = "darshu@gmail.com";
		String password = "Darsh@20";

		Employee employee = new Employee();
		employee.setEmployeeId(123456);
		employee.setName("darshan");
		employee.setEmail("darshu@gmail.com");
		employee.setPassword("Darsh@19");
		employee.setRole("Admin");
		employee.setDoj(LocalDate.parse("2023-05-19"));
		employee.setStatus(false);

		Mockito.when((employeeRepository.findByEmail(email))).thenReturn(employee);
		ResponseStructure result = employeeServiceImpl.adminLogIn(email, password);
		assertNotNull(result);
		assertEquals("Authentication Failed", result.getMessage());
	}

	@Test
	void adminLogInTestForNullEmployee() {
		String email = "darshu@gmail.com";
		String password = "Darsh@20";

		Employee employee = null;

		Mockito.when((employeeRepository.findByEmail(email))).thenReturn(employee);

		assertThrows(InvalidEmployeeException.class, () -> employeeServiceImpl.adminLogIn(email, password));
	}

	@Test
	void adminLogInTestForAuthorisation() {
		String email = "darshu@gmail.com";
		String password = "Darsh@20";

		Employee employee = new Employee();
		employee.setEmployeeId(123456);
		employee.setName("darshan");
		employee.setEmail("darshu@gmail.com");
		employee.setPassword("Darsh@19");
		employee.setRole("developer");
		employee.setDoj(LocalDate.parse("2023-05-19"));
		employee.setStatus(false);

		Mockito.when((employeeRepository.findByEmail(email))).thenReturn(employee);
		ResponseStructure result = employeeServiceImpl.adminLogIn(email, password);
		assertNotNull(result);
		assertEquals("Authorisation Failed", result.getMessage());
	}

	@Test
	void adminLogoutTest() {
		String email = "darshu@gmail.com";
		String password = "Darsh@19";

		Employee employee = new Employee();
		employee.setEmployeeId(123456);
		employee.setName("darshan");
		employee.setEmail("darshu@gmail.com");
		employee.setPassword("Darsh@19");
		employee.setRole("Admin");
		employee.setDoj(LocalDate.parse("2023-05-19"));
		employee.setStatus(true);
		Mockito.when((employeeRepository.findByEmail(email))).thenReturn(employee);
		ResponseStructure result = employeeServiceImpl.adminLogOut(email);
		assertNotNull(result);
		assertEquals("Log-out successfull", result.getMessage());

	}

	@Test
	void adminLogoutTestForNullEmployee() {
		String email = "darshu@gmail.com";
		String password = "Darsh@20";

		Employee employee = null;

		Mockito.when((employeeRepository.findByEmail(email))).thenReturn(employee);

		assertThrows(NullPointerException.class, () -> employeeServiceImpl.adminLogOut(email));
	}

	@Test
	void adminLogoutTestForEmployeeNotLogIn() {
		String email = "darshu@gmail.com";
		String password = "Darsh@19";

		Employee employee = new Employee();
		employee.setEmployeeId(123456);
		employee.setName("darshan");
		employee.setEmail("darshu@gmail.com");
		employee.setPassword("Darsh@19");
		employee.setRole("Admin");
		employee.setDoj(LocalDate.parse("2023-05-19"));
		employee.setStatus(false);
		Mockito.when((employeeRepository.findByEmail(email))).thenReturn(employee);

		assertThrows(InvalidEmployeeException.class, () -> employeeServiceImpl.adminLogOut(email));
	}

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

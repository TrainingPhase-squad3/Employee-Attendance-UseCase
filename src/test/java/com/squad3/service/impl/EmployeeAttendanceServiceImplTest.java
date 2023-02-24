package com.squad3.service.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.squad3.entity.Employee;
import com.squad3.entity.EmployeeAttendance;
import com.squad3.exception.AccessDeniedException;
import com.squad3.exception.EmployeeNotFoundException;
import com.squad3.exception.NotImplementedException;
import com.squad3.repository.EmployeeAttendanceRepository;
import com.squad3.repository.EmployeeRepository;
import com.squad3.response.SwipingResponse;

@ExtendWith(SpringExtension.class)
class EmployeeAttendanceServiceImplTest {
	@InjectMocks
	EmployeeAttendanceServiceImpl employeeAttendanceServiceImpl;

	@Mock
	EmployeeAttendanceRepository employeeAttendanceRepository;

	@Mock
	EmployeeRepository employeeRepository;

	@Test
	void testEmployeeAttendancePositive() {
		Employee employee = new Employee();
		employee.setEmployeeId(123456);
		employee.setName("Manoj");
		employee.setDoj(LocalDate.now());
		employee.setEmail("man@gmail.com");
		employee.setPassword("Man734567@");
		employee.setRole("Admin");
		employee.setStatus(true);

		Employee employee1 = new Employee();
		employee1.setEmployeeId(345678);
		employee1.setName("Sanoj");
		employee1.setDoj(LocalDate.now());
		employee1.setEmail("san@gmail.com");
		employee1.setPassword("San734567@");
		employee1.setRole("Others");
		employee1.setStatus(false);

		String fromDate = "12-08-2021";
		String toDate = "12-08-2023";

		EmployeeAttendance employeeAttendance = new EmployeeAttendance();
		employeeAttendance.setEmployee(employee1);
		employeeAttendance.setDate(LocalDate.now());
		employeeAttendance.setEmployeeAttendanceId(234567);
		employeeAttendance.setSwipeInTime(LocalTime.of(10, 23, 25));
		employeeAttendance.setSwipeOutTime(null);

		List<EmployeeAttendance> list = new ArrayList<>();
		list.add(employeeAttendance);

		Mockito.when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		Mockito.when(employeeRepository.findById(employee1.getEmployeeId())).thenReturn(Optional.of(employee1));

		Mockito.when(employeeAttendanceRepository.findByEmployee_EmployeeIdAndDateBetween(employee1.getEmployeeId(),
				LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")),
				LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")))).thenReturn(list);

		List<SwipingResponse> resultList = employeeAttendanceServiceImpl
				.employeeAttendenceHistory(employee.getEmployeeId(), employee1.getEmployeeId(), fromDate, toDate);

		List<SwipingResponse> list1 = list.stream().map(history -> {
			return new SwipingResponse(history.getDate(), history.getSwipeInTime(), history.getSwipeOutTime());
		}).toList();

		assertEquals(list1,list1);

	}

	@Test
	void testEmployeeAttendanceNullAdmin() {

		long empId = 123456;
		long adminId = 34567;
		String fromDate = "2021-08-12";
		String toDate = "2023-08-12";

		assertThrows(EmployeeNotFoundException.class,
				() -> employeeAttendanceServiceImpl.employeeAttendenceHistory(adminId, empId, fromDate, toDate));

	}

	@Test
	void testEmployeeAttendanceNullEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeId(123456);
		employee.setName("Manoj");
		employee.setDoj(LocalDate.now());
		employee.setEmail("man@gmail.com");
		employee.setPassword("Man734567@");
		employee.setRole("Admin");
		employee.setStatus(true);

		String fromDate = "2021-08-12";
		String toDate = "2023-08-12";

		long empId = 23456;
		Mockito.when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		Mockito.when(employeeRepository.findById(empId)).thenReturn(Optional.ofNullable(null))
				.thenThrow(new EmployeeNotFoundException("Employee:" + empId + " not found"));
		assertThatThrownBy(() -> employeeAttendanceServiceImpl.employeeAttendenceHistory(employee.getEmployeeId(),
				empId, fromDate, toDate)).isInstanceOf(EmployeeNotFoundException.class)
				.hasMessage("Employee:" + empId + " not found");

	}

	@Test
	void testEmployeeAttendanceUnauthorized() {
		Employee employee = new Employee();
		employee.setEmployeeId(3456);
		employee.setName("Manoj");
		employee.setDoj(LocalDate.now());
		employee.setEmail("man@gmail.com");
		employee.setPassword("Man734567@");
		employee.setRole("Others");

		long empId = 123456;
		String fromDate = "2021-08-12";
		String toDate = "2023-08-12";

		Mockito.when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		assertThrows(AccessDeniedException.class, () -> employeeAttendanceServiceImpl
				.employeeAttendenceHistory(employee.getEmployeeId(), empId, fromDate, toDate));

	}

	@Test
	void testEmployeeAttendanceLogin() {
		Employee employee = new Employee();
		employee.setEmployeeId(34567);
		employee.setName("Manoj");
		employee.setDoj(LocalDate.now());
		employee.setEmail("man@gmail.com");
		employee.setPassword("Man734567@");
		employee.setRole("Admin");
		employee.setStatus(false);

		long empId = 123456;
		String fromDate = "2021-08-12";
		String toDate = "2023-08-12";

		Mockito.when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		assertThrows(NotImplementedException.class, () -> employeeAttendanceServiceImpl
				.employeeAttendenceHistory(employee.getEmployeeId(), empId, fromDate, toDate));

	}

	@Test
	void testEmployeeAttendanceHistoryNull() {
		Employee employee = new Employee();
		employee.setEmployeeId(123456);
		employee.setName("Manoj");
		employee.setDoj(LocalDate.now());
		employee.setEmail("man@gmail.com");
		employee.setPassword("Man734567@");
		employee.setRole("Admin");
		employee.setStatus(true);

		Employee employee1 = new Employee();
		employee1.setEmployeeId(345678);
		employee1.setName("Sanoj");
		employee1.setDoj(LocalDate.now());
		employee1.setEmail("san@gmail.com");
		employee1.setPassword("San734567@");
		employee1.setRole("Others");
		employee1.setStatus(false);

		String fromDate = "12-08-2021";
		String toDate = "12-08-2023";

		Mockito.when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		Mockito.when(employeeRepository.findById(employee1.getEmployeeId())).thenReturn(Optional.of(employee1));
		Mockito.when(employeeAttendanceRepository.findByEmployee_EmployeeIdAndDateBetween(employee1.getEmployeeId(),
				LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")),
				LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"))))
				.thenThrow(new NotImplementedException("No swiping history in the specified dates"));
		assertThatThrownBy(() -> employeeAttendanceServiceImpl.employeeAttendenceHistory(employee.getEmployeeId(),
				employee1.getEmployeeId(), fromDate, toDate)).isInstanceOf(NotImplementedException.class)
				.hasMessage("No swiping history in the specified dates");

	}

}

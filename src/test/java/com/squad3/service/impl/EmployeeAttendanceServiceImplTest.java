package com.squad3.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.squad3.entity.Employee;
import com.squad3.entity.EmployeeAttendance;
import com.squad3.exception.EmployeeNotFoundException;
import com.squad3.repository.EmployeeAttendanceRepository;
import com.squad3.repository.EmployeeRepository;

@ExtendWith(SpringExtension.class)
class EmployeeAttendanceServiceImplTest {
	@InjectMocks
	EmployeeAttendanceServiceImpl employeeAttendanceServiceImpl;

	@Mock
	EmployeeAttendanceRepository employeeAttendanceRepository;

	@Mock
	EmployeeRepository employeeRepository;

	@Test
	void testSwippingFirst() {
		long employeeId=1;
		Employee employee=new Employee();
		employee.setEmployeeId(employeeId);
		EmployeeAttendance employeeAttendance=new EmployeeAttendance();
		employeeAttendance.setEmployeeAttendanceId(1);
		employeeAttendance.setEmployee(employee);
		employeeAttendance.setSwipeInTime(LocalTime.NOON);
		Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
		Mockito.when(employeeAttendanceRepository.findByEmployee_EmployeeIdAndDate(employeeId,LocalDate.now())).thenReturn(employeeAttendance);
		Mockito.when(employeeAttendanceRepository.save(employeeAttendance)).thenReturn(employeeAttendance);
		assertEquals(LocalTime.NOON, employeeAttendanceServiceImpl.swipping(employeeId).getSwipeInTime());
	}
	@Test
	void testSwippingSecond() {
		long employeeId=1;
		Mockito.when(employeeRepository.findById(employeeId)).thenThrow(new EmployeeNotFoundException("Employee with Id: "+employeeId+" not found"));
		assertThrows(EmployeeNotFoundException.class,
				() -> employeeAttendanceServiceImpl.swipping(employeeId));

	}
	@Test
	void testSwippingThird() {
		long employeeId=1;
		Employee employee=new Employee();
		employee.setEmployeeId(employeeId);
		Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
		Mockito.when(employeeAttendanceRepository.findByEmployee_EmployeeIdAndDate(employeeId,LocalDate.now())).thenReturn(null);
		assertNull(employeeAttendanceServiceImpl.swipping(employeeId));
	}
}
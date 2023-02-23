package com.squad3.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAttendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeeAttendanceId;
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;
	private LocalTime swipeInTime;
	private LocalTime swipeOutTime;
	private LocalDate date;
}

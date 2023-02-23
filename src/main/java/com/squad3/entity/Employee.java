package com.squad3.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
	@Id
	private long employeeId;
	private String name;
	private String role;
	@Column(unique = true)
	private String email;
	private String password;
	@CreationTimestamp
	private LocalDate doj;
	private boolean status;
}

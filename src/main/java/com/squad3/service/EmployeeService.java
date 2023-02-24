package com.squad3.service;

public interface EmployeeService {
	

ResponseStructure adminLogIn(String email, String password);

	ResponseStructure adminLogOut(String email);



}

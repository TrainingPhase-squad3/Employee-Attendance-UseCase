package com.squad3.service;

public interface EmployeeService {
Response save(EmployeeDto employeedto);
	
ResponseStructure adminLogIn(String email, String password);

ResponseStructure adminLogOut(String email);





}

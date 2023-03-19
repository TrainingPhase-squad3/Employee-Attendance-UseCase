package com.squad3.service;

import com.squad3.dto.EmployeeDto;
import com.squad3.response.Response;
import com.squad3.response.ResponseStructure;

public interface EmployeeService {
	Response save(EmployeeDto employeedto);

	ResponseStructure adminLogIn(String email, String password);

	ResponseStructure adminLogOut(String email);

}

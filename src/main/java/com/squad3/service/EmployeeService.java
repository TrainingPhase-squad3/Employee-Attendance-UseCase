package com.squad3.service;

import com.squad3.response.ResponseStructure;

public interface EmployeeService {
	ResponseStructure adminLogIn(String email, String password);

	ResponseStructure adminLogOut(String email);

}

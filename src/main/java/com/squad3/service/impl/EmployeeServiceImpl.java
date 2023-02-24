package com.squad3.service.impl;

import org.springframework.stereotype.Service;

import com.squad3.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public ResponseStructure adminLogIn(String email, String password) {
		ResponseStructure structure = new ResponseStructure();
		Employee employee = employeeRepository.findByEmail(email);
		if (employee != null && employee.getRole().equals("Admin")) {
			if (employee.isStatus()) {
				logger.info("Employee already Logged In");
				structure.setMessage(" admin already Logged In ");
				structure.setStatus(HttpStatus.ALREADY_REPORTED);

			} else if (email.equals(employee.getEmail()) && password.equals(employee.getPassword())) {
				logger.info("Employee Logged In");
				employee.setStatus(true);
				employeeRepository.save(employee);
				structure.setMessage("Logged In successfully");
				structure.setStatus(HttpStatus.OK);
			} else {
				logger.warn(" invalid credentials");
				structure.setMessage("Authentication Failed");
				structure.setStatus(HttpStatus.BAD_REQUEST);
			}

		} else if (employee == null) {
			logger.warn(" invalid Employee");
			throw new InvalidEmployeeException("Invalid Employee");

		} else {
			logger.warn("Authorisation Failed");
			structure.setMessage("Authorisation Failed");
			structure.setStatus(HttpStatus.UNAUTHORIZED);
		}

		return structure;
	}

	@Override
	public ResponseStructure adminLogOut(String email) {
		ResponseStructure structure = new ResponseStructure();
		Employee employee = employeeRepository.findByEmail(email);
		if (email != null && employee.isStatus()) {
			employee.setStatus(false);
			employeeRepository.save(employee);
			logger.info("admin logged out");
			structure.setMessage("Log-out successfull");
			structure.setStatus(HttpStatus.OK);
			return structure;

		} else {
			throw new InvalidEmployeeException("Enter valid credential");
		}

	}
	
}

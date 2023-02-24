package com.squad3.service.impl;

import org.springframework.stereotype.Service;

import com.squad3.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {


	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Response save(EmployeeDto employeedto) throws EmployeeAlreadyExists {
		Employee employee = employeeRepository.findByEmail(employeedto.getEmail());
		if (employee != null) {
			if (employeedto.getPassword().equals(employee.getPassword()))
				return new Response("Employee Already Registered");
			return new Response("Incorrect  password");

		}

		employeeRepository.save(Employee.builder().employeeId((long) (Math.random() * (999999 - 100000) + 1) + 10000)
				.name(employeedto.getName()).role(employeedto.getRole()).password(employeedto.getPassword())
				.email(employeedto.getEmail()).build());
		return new Response("Employee Registered successfully");
	}
	@Override
	public List<SwipingResponse> employeeAttendenceHistory(long adminId, long empId, String fromDate, String toDate) {
		Employee employee = employeeRepository.findById(adminId)
				.orElseThrow(() -> new EmployeeNotFoundException("Admin:" + adminId + " not found"));
		if (employee.getRole().equals("Admin")) {
			logger.info("Employee is Admin");
			if (employee.isStatus()) {
				logger.info("Admin logged in");
				Employee employee1 = employeeRepository.findById(empId)
						.orElseThrow(() -> new EmployeeNotFoundException("Employee:" + empId + " not found"));

				List<EmployeeAttendance> list = employeeAttendanceRepository.findByEmployee_EmployeeIdAndDateBetween(
						employee1.getEmployeeId(), LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")),
						LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
				if (list.isEmpty()) {
					logger.warn("empty list due to no attendance history for employee");
					throw new NotImplementedException("No swiping history in the specified dates");

				} else {
					return list.stream().map(history -> {
						return new SwipingResponse(history.getDate(), history.getSwipeInTime(),
								history.getSwipeOutTime());
					}).toList();
				}

			} else {
				logger.error("Admin not logged in");
				throw new NotImplementedException("Not logged in");
			}

		} else {
			logger.error("unauthorized employee");
			throw new AccessDeniedException("Unauthorized access");

		}
}

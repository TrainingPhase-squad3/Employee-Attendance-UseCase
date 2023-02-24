package com.squad3.service;


import java.util.List;

import com.squad3.response.SwipingResponse;

public interface EmployeeAttendanceService {
	EmployeeAttendance swipping(long employeeId);
	public List<SwipingResponse> employeeAttendenceHistory(long adminId, long empId, String fromDate, String toDate);
	
}

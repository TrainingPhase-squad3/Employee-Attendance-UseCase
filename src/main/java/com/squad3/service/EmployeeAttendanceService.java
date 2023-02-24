package com.squad3.service;


import java.util.List;

import com.squad3.response.SwipingResponse;

public interface EmployeeAttendanceService {

	List<SwipingResponse> employeeAttendenceHistory(long adminId, long employeeId, String fromDate, String toDate);
}

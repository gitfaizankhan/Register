package com.PSL.management.employeeAttendenceAdvanceSearch;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.PSL.management.employeeModel.EmployeeAttendenceEntity;

@Repository
public interface AttendenceAdvanceSearchDao {
	List<EmployeeAttendenceEntity> employeeAttenceList(String employeename, String employeeid, String attendencedate,
			String entrytime, String exittime);
}

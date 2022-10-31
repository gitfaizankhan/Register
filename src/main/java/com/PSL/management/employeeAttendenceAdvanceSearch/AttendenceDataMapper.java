package com.PSL.management.employeeAttendenceAdvanceSearch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.PSL.management.employeeModel.EmployeeAttendenceEntity;

public class AttendenceDataMapper implements RowMapper<EmployeeAttendenceEntity>{

	@Override
	public EmployeeAttendenceEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeAttendenceEntity employeeAttendenceEntity = new EmployeeAttendenceEntity();
		employeeAttendenceEntity.setId(rs.getLong("id"));
		employeeAttendenceEntity.setEmployeeid(rs.getString("employeeid"));
		employeeAttendenceEntity.setEmployeename(rs.getString("employeename"));
		employeeAttendenceEntity.setAttendencedate(rs.getDate("attendencedate"));
		employeeAttendenceEntity.setEntrytime(rs.getDate("entrytime"));
		employeeAttendenceEntity.setExittime(rs.getDate("exittime"));
		return employeeAttendenceEntity;
	}

}

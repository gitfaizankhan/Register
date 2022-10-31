package com.PSL.management.absentDataAdvanceSearch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.PSL.management.employeeModel.EmployeeAbsentDetails;

public class AbsentDataMapper implements RowMapper<EmployeeAbsentDetails> {

	
//  ____________________About_______________________________
//	RowMapper Class of Entity Class -->EmployeeAbsentDetails.
//	Used to map Row from the database.
//	Used in ---> AbsentAdvanceSearchDaoImp Class.
	@Override
	public EmployeeAbsentDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EmployeeAbsentDetails employeeAbsentDetails = new EmployeeAbsentDetails();
		employeeAbsentDetails.setId(rs.getLong("id"));
		employeeAbsentDetails.setEmployeeid(rs.getString("employeeid"));
		employeeAbsentDetails.setEmployeename(rs.getString("employeename"));
		employeeAbsentDetails.setEmmloyeeabsentdate(rs.getDate("emloyeeabsentdate"));
		employeeAbsentDetails.setLeave(rs.getString("off_type"));
		return employeeAbsentDetails;
	}

}

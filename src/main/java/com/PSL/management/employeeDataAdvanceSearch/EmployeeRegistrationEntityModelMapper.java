package com.PSL.management.employeeDataAdvanceSearch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.PSL.management.employeeModel.EmployeeRegistrationEntityModel;

public class EmployeeRegistrationEntityModelMapper implements RowMapper<EmployeeRegistrationEntityModel> {

	@Override
	public EmployeeRegistrationEntityModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EmployeeRegistrationEntityModel employeeRegistrationEntityModel = new EmployeeRegistrationEntityModel();
		
		employeeRegistrationEntityModel.setEmployeeid(rs.getString("employeeid"));
		employeeRegistrationEntityModel.setEmployeefirstname(rs.getString("employeefirstname"));
		employeeRegistrationEntityModel.setEmployeelastname(rs.getString("employeelastname"));
		employeeRegistrationEntityModel.setEmployeeaddress(rs.getString("employeeaddress"));
		employeeRegistrationEntityModel.setEmployeemial(rs.getString("employeemial"));
		employeeRegistrationEntityModel.setEmployeejoindate(rs.getDate("employeejoindate"));
		employeeRegistrationEntityModel.setEmployeeleavedate(rs.getDate("employeeleavedate"));
		employeeRegistrationEntityModel.setEmployeephonenumber(rs.getString("employeephonenumber"));
		employeeRegistrationEntityModel.setEmployeeregistrationdate(rs.getDate("employeeregistrationdate"));
		return employeeRegistrationEntityModel;
	}
}

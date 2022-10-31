package com.PSL.management.employeeDataAdvanceSearch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.PSL.management.employeeModel.EmployeeRegistrationEntityModel;

@Service
public class EmployeeManageDataAdvanceSearchDaoImp implements EmployeeManageDataAdvanceSearchDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	EmployeeRegistrationEntityModelMapper employeeRegistrationEntityModelMapper = new EmployeeRegistrationEntityModelMapper();

	@Override
	public List<EmployeeRegistrationEntityModel> findRegisteredEmployeeData(String employeeid, String employeefirstname,
			String employeelastname, String employeeusername, String employeephonenumber, String employeemial,
			String employeeaddress, String employeejoindate, String employeeregistrationdate,
			String employeeleavedate) {
		String employee_database_query = "SELECT * FROM employeedata WHERE 1 = 1";

		if (employeeid != null) {
			employee_database_query = employee_database_query + " AND employeeid like '%" + employeeid + "%'";
		}
		if (employeefirstname != null) {
			employee_database_query = employee_database_query + " AND employeefirstname like '%" + employeefirstname
					+ "%'";
		}
		if (employeelastname != null) {
			employee_database_query = employee_database_query + " AND employeelastname like '%" + employeelastname
					+ "%'";
		}
		if (employeeusername != null) {
			employee_database_query = employee_database_query + " AND employeeusername like '%" + employeeusername
					+ "%'";
		}
		if (employeephonenumber != null) {
			employee_database_query = employee_database_query + " AND employeephonenumber like '%" + employeephonenumber
					+ "%'";
		}
		if (employeemial != null) {
			employee_database_query = employee_database_query + " AND employeemial like '%" + employeemial + "%'";
		}
		if (employeeaddress != null) {
			employee_database_query = employee_database_query + " AND employeeaddress like '%" + employeeaddress + "%'";
		}
		if (employeejoindate != null) {
			employee_database_query = employee_database_query + " AND employeejoindate like '%" + employeejoindate
					+ "%'";
		}
		if (employeeregistrationdate != null) {
			employee_database_query = employee_database_query + " AND employeeregistrationdate like '%"
					+ employeeregistrationdate + "%'";
		}
		if (employeeleavedate != null) {
			employee_database_query = employee_database_query + " AND employeeleavedate like '%" + employeeleavedate
					+ "%'";
		}
		List<EmployeeRegistrationEntityModel> registeredEmployeeList = jdbcTemplate.query(employee_database_query,
				employeeRegistrationEntityModelMapper);
		return registeredEmployeeList;
	}

}

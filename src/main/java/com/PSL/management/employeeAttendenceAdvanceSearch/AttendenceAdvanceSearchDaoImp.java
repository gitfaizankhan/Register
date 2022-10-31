package com.PSL.management.employeeAttendenceAdvanceSearch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.PSL.management.employeeModel.EmployeeAttendenceEntity;

@Service
public class AttendenceAdvanceSearchDaoImp implements AttendenceAdvanceSearchDao{

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	AttendenceDataMapper attendenceDataMapper = new AttendenceDataMapper();
	
	@Override
	public List<EmployeeAttendenceEntity> employeeAttenceList(String employeename, String employeeid,
			String attendencedate, String entrytime, String exittime) {
		
		String attendence_data_query = "SELECT * FROM employeeattendencedata WHERE 1 = 1";
		if(employeename != null) {
			attendence_data_query = attendence_data_query + " AND employeename like '%"+employeename+"%'";
		}
		if(employeeid != null) {
			attendence_data_query = attendence_data_query + " AND employeeid like '%"+employeeid+"%'";
		}
		if(attendencedate != null) {
			attendence_data_query = attendence_data_query + " AND attendencedate like '%"+attendencedate+"%'";
		}
		if(entrytime != null) {
			attendence_data_query = attendence_data_query + " AND entrytime like '%"+entrytime+"%'";
		}
		if(exittime != null) {
			attendence_data_query = attendence_data_query + " AND exittime like '%"+exittime+"%'";
		}
		List<EmployeeAttendenceEntity> employeeAttendencelist = jdbcTemplate.query(attendence_data_query, attendenceDataMapper);
		return employeeAttendencelist;
	}

}

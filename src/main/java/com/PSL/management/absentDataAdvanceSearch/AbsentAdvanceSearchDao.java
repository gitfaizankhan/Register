package com.PSL.management.absentDataAdvanceSearch;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.PSL.management.employeeModel.EmployeeAbsentDetails;

@Repository
public interface AbsentAdvanceSearchDao {
	
	List<EmployeeAbsentDetails> employeeAbsentList(String employeename, String employeeid, String leave,
			String emloyeeabsentdate);

}

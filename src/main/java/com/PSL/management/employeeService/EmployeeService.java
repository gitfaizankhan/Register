package com.PSL.management.employeeService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PSL.management.employeeModel.EmployeeAbsentDetails;
import com.PSL.management.employeeModel.EmployeeAttendenceEntity;
import com.PSL.management.employeeModel.EmployeeMonthlyStatusEntity;
import com.PSL.management.employeeModel.EmployeeRegistrationEntityModel;
import com.PSL.management.employeeModelRepository.EmployeeAbsentRepository;
import com.PSL.management.employeeModelRepository.EmployeeAttendenceDataRepository;
import com.PSL.management.employeeModelRepository.EmployeeMonthlyStatusRepository;
import com.PSL.management.employeeModelRepository.EmployeeRegistrationModelRepository;



@Service
public class EmployeeService {

	@Autowired
	private EmployeeRegistrationModelRepository employeeRegistrtionModelRepository;

	@Autowired
	private EmployeeAttendenceDataRepository employeeAttendenceDataRepository;

	@Autowired
	private EmployeeAbsentRepository employeeAbsentRepository;
	
	@Autowired
	private EmployeeMonthlyStatusRepository employeeMonthlyStatusRepository;

	


	public EmployeeRegistrationEntityModel addEmployee(EmployeeRegistrationEntityModel employeeRegistrationEntityModel) {
		return employeeRegistrtionModelRepository.save(employeeRegistrationEntityModel);
	}


	public List<EmployeeRegistrationEntityModel> EmployeeAllEmployeeList() {
    	return employeeRegistrtionModelRepository.findAll();
    }


	public  EmployeeRegistrationEntityModel  EmployeeDetailsByID(long id) {
    	return employeeRegistrtionModelRepository.findById(id).orElse(null);
	}

	public EmployeeRegistrationEntityModel updateEmployeeDetails(EmployeeRegistrationEntityModel employeeRegistrationEntityModel) {
		return employeeRegistrtionModelRepository.save(employeeRegistrationEntityModel);
	}


	public void deleteEmployee(long id) {
		employeeRegistrtionModelRepository.deleteById(id);
    }

	public EmployeeAttendenceEntity addEmployeeAttendenceDetails(EmployeeAttendenceEntity employeeAttendenceEntity) {
		return employeeAttendenceDataRepository.save(employeeAttendenceEntity);
	}

	public EmployeeAbsentDetails addEmployeeAbsentDetails(EmployeeAbsentDetails employeeAttendenceEntity) {
		return employeeAbsentRepository.save(employeeAttendenceEntity);
	}
	
	
	public EmployeeMonthlyStatusEntity addEmployeeMonthlyAttendenceDetails(EmployeeMonthlyStatusEntity employeeMonthlyStatusEntity) {
		return employeeMonthlyStatusRepository.save(employeeMonthlyStatusEntity);
	}
	
	public EmployeeMonthlyStatusEntity updateEmployeeMonthlyDetails(EmployeeMonthlyStatusEntity employeeMonthlyStatusEntity) {
		return employeeMonthlyStatusRepository.save(employeeMonthlyStatusEntity);
	}
}

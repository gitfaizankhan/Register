package com.PSL.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PSL.management.dataModel.Register;
import com.PSL.management.employeeModel.EmployeeAbsentDetails;
import com.PSL.management.employeeModel.EmployeeAttendenceEntity;
import com.PSL.management.employeeModel.EmployeeMonthlyStatusEntity;
import com.PSL.management.employeeModel.EmployeeRegistrationEntityModel;

@Service
public interface IRegisterService {

	List<Register> findPaginated(int pageNo, int pageSize);
	
	List<EmployeeRegistrationEntityModel> findEmployeePagination(int pageNo, int pageSize);
	
	List<EmployeeAbsentDetails>  findEmployeeAbsentPage(int pageNo, int pageSize);
	
	List<EmployeeAttendenceEntity> findEmployeePresentPage(int pageNo, int pageSize);
	
	List<EmployeeMonthlyStatusEntity> findEmployeeMonthlyStatusPage(int pageNo, int PageSize);
	
	
}

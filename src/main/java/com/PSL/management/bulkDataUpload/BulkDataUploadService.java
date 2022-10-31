package com.PSL.management.bulkDataUpload;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PSL.management.dataModel.Register;
import com.PSL.management.employeeModel.EmployeeAbsentDetails;
import com.PSL.management.employeeModel.EmployeeAttendenceEntity;
import com.PSL.management.employeeModel.EmployeeRegistrationEntityModel;
import com.PSL.management.employeeModelRepository.EmployeeAbsentRepository;
import com.PSL.management.employeeModelRepository.EmployeeAttendenceDataRepository;
import com.PSL.management.employeeModelRepository.EmployeeMonthlyStatusRepository;
import com.PSL.management.employeeModelRepository.EmployeeRegistrationModelRepository;
import com.PSL.management.repository.RegisterDao;

@Service
public class BulkDataUploadService {

	
	@Autowired
	private RegisterDao registerDao;
	
	@Autowired
	private EmployeeAbsentRepository employeeAbsentRepository;
	
	@Autowired
	private EmployeeAttendenceDataRepository employeeAttendenceDataRepository;

	@Autowired
	private EmployeeRegistrationModelRepository employeeRegistrationModelRepository;
	
	public void adminsave(MultipartFile file, String filename) {
		try {
			List<Register> register = AdminRegisterHelper.convertExcelToListOfProduct(file.getInputStream(), filename);
			this.registerDao.saveAll(register);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void absentEmployeeSave(MultipartFile file, String filename) {
		try {
			List<EmployeeAbsentDetails> employeeAbsent = AbsentEmployeeHelper.convertExcelToListOfProduct(file.getInputStream(), filename);
			this.employeeAbsentRepository.saveAll(employeeAbsent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void presentEmployeeSave(MultipartFile file, String filename) {
		try {
			List<EmployeeAttendenceEntity> employeePresent = PresentEmployeeHelper.convertExcelToListOfProduct(file.getInputStream(), filename);
			this.employeeAttendenceDataRepository.saveAll(employeePresent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void registerEmployeesSave(MultipartFile file, String filename) {
		try {
			List<EmployeeRegistrationEntityModel> employeeRegistration = RegisterEmployeesHelper.convertExcelToListOfProduct(file.getInputStream(), filename);
			this.employeeRegistrationModelRepository.saveAll(employeeRegistration);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

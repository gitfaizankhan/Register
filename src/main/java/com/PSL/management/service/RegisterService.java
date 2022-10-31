package com.PSL.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.PSL.management.dataModel.Register;
import com.PSL.management.dataModel.SessionData;
import com.PSL.management.employeeModel.EmployeeAbsentDetails;
import com.PSL.management.employeeModel.EmployeeAttendenceEntity;
import com.PSL.management.employeeModel.EmployeeMonthlyStatusEntity;
import com.PSL.management.employeeModel.EmployeeRegistrationEntityModel;
import com.PSL.management.employeeModelRepository.EmployeeAbsentRepository;
import com.PSL.management.employeeModelRepository.EmployeeAttendenceDataRepository;
import com.PSL.management.employeeModelRepository.EmployeeMonthlyStatusRepository;
import com.PSL.management.employeeModelRepository.EmployeeRegistrationModelRepository;
import com.PSL.management.repository.RegisterDao;
import com.PSL.management.repository.SessionDao;

@Service
public class RegisterService implements IRegisterService {

	@Autowired
	private RegisterDao dao;

	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private RegisterDao adminRepo;
	
	@Autowired
	private EmployeeAbsentRepository employeeAbsentRepository;
	
	
	@Autowired
	private EmployeeAttendenceDataRepository employeeAttendenceDataRepository;
	
	
	@Autowired
	private EmployeeMonthlyStatusRepository employeeMonthlyStatusRepository;
	
	@Autowired
	private EmployeeRegistrationModelRepository employeeRegistrationModelRepository;

	public Register addUsers(Register register) {
		return dao.save(register);
	}

	public Register getUsers(String username) {
		return dao.findByUsername(username);
	}

	public SessionData addSession(SessionData sessionData) {
		return sessionDao.save(sessionData);
	}

	public void deleteUsers(long id) {
		dao.deleteById(id);
	}

	public Register updateProducts(Register register) {
		return dao.save(register);
	}

	public List<Register> getUsersList() {
		return dao.findAll();
	}

	@Override
	public List<Register> findPaginated(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Register> pageResult = adminRepo.findAll(paging);
		return pageResult.toList();
	}

	@Override
	public List<EmployeeRegistrationEntityModel> findEmployeePagination(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<EmployeeRegistrationEntityModel> pageResult = employeeRegistrationModelRepository.findAll(paging);
		return pageResult.toList();
	}

	@Override
	public List<EmployeeAbsentDetails> findEmployeeAbsentPage(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<EmployeeAbsentDetails> pageResult = employeeAbsentRepository.findAll(paging);
		return pageResult.toList();
	}


	@Override
	public List<EmployeeMonthlyStatusEntity> findEmployeeMonthlyStatusPage(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<EmployeeMonthlyStatusEntity> pageResult = employeeMonthlyStatusRepository.findAll(paging);
		return pageResult.toList();
	}

	@Override
	public List<EmployeeAttendenceEntity> findEmployeePresentPage(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<EmployeeAttendenceEntity> pageResult = employeeAttendenceDataRepository.findAll(paging);
		return pageResult.toList();
	}
    
}

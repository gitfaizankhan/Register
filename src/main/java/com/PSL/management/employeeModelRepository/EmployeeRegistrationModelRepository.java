package com.PSL.management.employeeModelRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.PSL.management.employeeModel.EmployeeRegistrationEntityModel;

@Repository
public interface EmployeeRegistrationModelRepository extends JpaRepository<EmployeeRegistrationEntityModel, Long>,
		PagingAndSortingRepository<EmployeeRegistrationEntityModel, Long> {

	@Query("SELECT employeeUsername FROM EmployeeRegistrationEntityModel employeeUsername WHERE employeeUsername.employeeusername=?1")
	EmployeeRegistrationEntityModel findByUsername(String empUserName);

}

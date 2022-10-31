package com.PSL.management.employeeModelRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.PSL.management.employeeModel.EmployeeAttendenceEntity;

@Repository
public interface EmployeeAttendenceDataRepository extends JpaRepository<EmployeeAttendenceEntity, Long>,
		PagingAndSortingRepository<EmployeeAttendenceEntity, Long> {

}

package com.PSL.management.employeeModelRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.PSL.management.employeeModel.EmployeeAbsentDetails;

@Repository
public interface EmployeeAbsentRepository
		extends JpaRepository<EmployeeAbsentDetails, Long>, PagingAndSortingRepository<EmployeeAbsentDetails, Long> {

	@Query("SELECT u FROM EmployeeAbsentDetails u WHERE u.employeename=?1")
	List<EmployeeAbsentDetails> findByUsername(String employeename);
}

package com.PSL.management.employeeModelRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.PSL.management.employeeModel.EmployeeMonthlyStatusEntity;
@Repository
public interface EmployeeMonthlyStatusRepository extends JpaRepository<EmployeeMonthlyStatusEntity, Long>,
		PagingAndSortingRepository<EmployeeMonthlyStatusEntity, Long> {

}

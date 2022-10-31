package com.PSL.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.PSL.management.dataModel.Register;

@Repository
public interface RegisterDao extends JpaRepository<Register, Long>, PagingAndSortingRepository<Register, Long> {

	@Query("SELECT register FROM Register register WHERE register.username=?1")
	Register findByUsername(String userName);

	@Query(value="DELETE register FROM Register register WHERE register.username=?1",nativeQuery = true)
	Register deleteUser(String username);
	

}

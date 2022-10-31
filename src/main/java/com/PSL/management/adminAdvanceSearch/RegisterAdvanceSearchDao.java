package com.PSL.management.adminAdvanceSearch;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.PSL.management.dataModel.Register;

@Repository
public interface RegisterAdvanceSearchDao {

	List<Register> searchAdminData(Long id, String firstname, String lastname, String username, String createdBy,
			String modifyBy);

}

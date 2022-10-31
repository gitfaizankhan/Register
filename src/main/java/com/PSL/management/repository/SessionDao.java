package com.PSL.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.PSL.management.dataModel.SessionData;

@Repository
public interface SessionDao extends JpaRepository<SessionData, Long> {
	
	@Query("SELECT u FROM SessionData u WHERE u.sessionid = ?1")
	public SessionData findByUserName(String sessionid);

}

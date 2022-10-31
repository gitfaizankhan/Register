package com.PSL.management.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PSL.management.Security.SessionDataconfig;
import com.PSL.management.dataModel.SessionData;
import com.PSL.management.repository.SessionDao;

@Service
public class SessionDataService {
	@Autowired
	SessionDao sessionDao;

	public ByteArrayInputStream load() {
		List<SessionData> sessionsData = sessionDao.findAll();
		ByteArrayInputStream in = SessionDataconfig.sessionDataDBToCSV(sessionsData);
		return in;
	}
}
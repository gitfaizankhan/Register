package com.PSL.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PSL.management.dataModel.Register;
import com.PSL.management.repository.RegisterDao;

@Service
public abstract class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RegisterDao dao;

	public UserDetails loadUserByUsername(Long username) throws UsernameNotFoundException {
		Register register = dao.findById(username).orElse(null);
		UserDetails details=null;
		if(register==null) {
			details = new UserDetailsImpl(register);
		}
		else
			throw new UsernameNotFoundException("User with " + username + "does not exits...");
		System.out.println(register);
		return details;
	}
}

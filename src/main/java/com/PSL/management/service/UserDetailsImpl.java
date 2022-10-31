package com.PSL.management.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.PSL.management.dataModel.Register;

@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {

	private Register register;

	public UserDetailsImpl(Register register) {
		this.register = register;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return register.getPassword();
	}

	@Override
	public String getUsername() {
		return register.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

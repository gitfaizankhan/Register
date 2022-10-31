package com.PSL.management.Security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PSL.management.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@RequestMapping("/Register")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService service;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").access("hasRole()");
		http.formLogin().loginProcessingUrl("/login").failureForwardUrl("/Register/login").usernameParameter("username")
				.passwordParameter("password").permitAll();

		http.logout().deleteCookies().logoutSuccessUrl("/login?logout");
		http.sessionManagement().maximumSessions(1).expiredUrl("/login");
	}

	@SuppressWarnings("unused")
	private SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
		SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter("redirect:/Register/home");
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("Authentication method called...");
		auth.authenticationProvider(authticationProvider());
		auth.userDetailsService(service).passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		System.out.println("Password encoder bean created...");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		SessionRegistry registry = new SessionRegistryImpl();
		return registry;
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public DaoAuthenticationProvider authticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(service);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return null;
			}
		};
	}

	private static final Map<String, HttpSession> sessions = new HashMap<>();

	public List<HttpSession> getActiveSessions() {
		return new ArrayList<>(sessions.values());
	}

	@Bean
	public HttpSessionListener httpSessionListener() {
		return new HttpSessionListener() {
			@Override
			public void sessionCreated(HttpSessionEvent hse) {
				sessions.put(hse.getSession().getId(), hse.getSession());

			}
			@Override
			public void sessionDestroyed(HttpSessionEvent hse) {
				sessions.remove(hse.getSession().getId());
			}
		};
	}
}
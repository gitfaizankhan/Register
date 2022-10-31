package com.PSL.management;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.PSL.management.dataModel.Register;
import com.PSL.management.repository.RegisterDao;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterApplication {

	@Autowired
	private RegisterDao registerDao;

	Date date = new Date();

	Register registeruser = new Register();

	@PostConstruct
	public void initUsers() {

		boolean value = (registerDao.findByUsername("zuhaib") != null);

		if (value) {
		} else {
			registeruser.setFirstname("faizan");
			registeruser.setLastname("khan");
			registeruser.setUsername("zuhaib");
			String password = new BCryptPasswordEncoder().encode("123");
			registeruser.setPassword(password);
			registeruser.setCreatedDate(date);
			registeruser.setModifyDate(date);
			registeruser.setCreatedBy("zuhaib");
			registeruser.setModifyBy("zuhaib");
			List<Register> list = List.of(registeruser);
			registerDao.saveAll(list);
		}
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RegisterApplication.class, args);
	}

}

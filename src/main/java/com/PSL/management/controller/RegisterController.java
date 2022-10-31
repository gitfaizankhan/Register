package com.PSL.management.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.PSL.management.adminAdvanceSearch.RegisterAdvanceSearchDao;
import com.PSL.management.bulkDataUpload.AdminRegisterHelper;
import com.PSL.management.bulkDataUpload.BulkDataUploadService;
import com.PSL.management.dataModel.Register;
import com.PSL.management.dataModel.SessionData;
import com.PSL.management.repository.RegisterDao;
import com.PSL.management.repository.SessionDao;
import com.PSL.management.service.IRegisterService;
import com.PSL.management.service.RegisterService;
import com.PSL.management.service.SessionDataService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/Admin")
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private RegisterDao daodb;

	@Autowired
	private RegisterService service;

	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private SessionDataService sessionDataService;

	@Autowired
	private IRegisterService iRegisterService;

	@Autowired
	private RegisterAdvanceSearchDao registerAdvanceSearchDao;

	@Autowired
	private BulkDataUploadService bulkDataUploadService;

	public void addSession(HttpServletRequest request, Register register) {
		String msg = null;
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) request.getSession().getAttribute("session");
		if (messages == null) {
			messages = new ArrayList<>();
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(30);
			session.invalidate();
		}
		messages.add(msg);
		request.getSession().setAttribute(register.getUsername(), messages);

	}

//	____________________________________________1______________________________________________

	@PostMapping("/newAdmin")
	public Register addUser(@RequestBody @AuthenticationPrincipal Register register, HttpServletRequest request,
			SessionData sessionData) {

		HttpSession session = request.getSession(false);

		if (session == null || !request.isRequestedSessionIdValid()) {
		} else {
			Register user = daodb.findByUsername(register.getUsername());
			if (user != null) {
				return user;
			} else {
				String password = register.getPassword();
				String encoders = bCryptPasswordEncoder.encode(password);
				register.setPassword(encoders);
				Date date = new Date();
				register.setCreatedDate(date);
				register.setModifyDate(date);
				service.addUsers(register);
				String ss = session.getId();
				SessionData user1 = sessionDao.findByUserName(ss);
				String sa = user1.getSessionid();
				if (ss.equals(sa)) {
					String username = user1.getUsername();
					register.setCreatedBy(username);
					register.setModifyBy(username);
					service.addUsers(register);
				} else {
				}
			}
		}
		return register;
	}

//	____________________________________________2______________________________________________
	
	@PostMapping("/loginAdmin")
	public String persistMessage(@RequestBody Register register, SessionData sessionData, String msg,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String encoders = register.getPassword();

		Register req = daodb.findByUsername(register.getUsername());
		System.out.println("encoded password  " + req.getPassword());
		boolean isPasswordMatch = bCryptPasswordEncoder.matches(encoders, req.getPassword());

		if (isPasswordMatch) {
			addSession(request, req);
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(300);
			@SuppressWarnings("unused")
			SessionData regi = service.addSession(sessionData);
			Long sat = session.getCreationTime();
			String s = String.valueOf(sat);
			sessionData.setSessionAccessTime(s);
			String si = session.getId();
			sessionData.setSessionid(si);
			int set1 = session.getMaxInactiveInterval();
			String s2 = String.valueOf(set1);
			sessionData.setMaxInInterval(s2);
			Long sessionEndTime = session.getLastAccessedTime() + session.getMaxInactiveInterval();
			String s1 = String.valueOf(sessionEndTime);
			sessionData.setSessionEndTime(s1);
			String currentSessionUserName = register.getUsername();
			sessionData.setUsername(currentSessionUserName);
			System.out.println("encoded password You are Successfully Login:");
			return "User Login Successfully...";
		} else {
			return "Invalid Password...";
		}
	}

//	____________________________________________3______________________________________________

	@GetMapping("/allAdminDetails")
	public List<Register> getUserList(HttpServletRequest request, Register register,
			@RequestParam(name = "pageNo") int pageNo, @RequestParam(name = "pageSize") int pageSize) {
		@SuppressWarnings("unused")
		List<Register> registers = null;
		HttpSession session1 = request.getSession(false);
		if (session1 == null || !request.isRequestedSessionIdValid()) {

		} else {
			registers = service.getUsersList();
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(300);
		}
		return iRegisterService.findPaginated(pageNo, pageSize);
	}

//	____________________________________________4______________________________________________

	@GetMapping("/AdminDetailsByUsername")
	public Register getUser(@RequestParam(name = "username") String username, HttpServletRequest request) {
		Register registers = null;
		HttpSession session1 = request.getSession(false);
		if (session1 == null || !request.isRequestedSessionIdValid()) {
		} else {
			registers = service.getUsers(username);
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(300);
		}
		return registers;
	}

//	____________________________________________5______________________________________________

	@DeleteMapping("/deleteAdmin/{id}")
	public String deleteUser(@PathVariable("id") long id, HttpServletRequest request) {
		HttpSession session1 = request.getSession(false);
		if (session1 == null || !request.isRequestedSessionIdValid()) {
			return "you are logout";
		} else {
			service.deleteUsers(id);
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(300);
			return "User Deleted Successfully...";
		}

	}

//	____________________________________________6______________________________________________
	@PutMapping("/UpdateAdminDetailByID")
	public Register updateUser(@RequestBody Register register, @RequestParam(name = "id") long id,
			HttpServletRequest request) {

		@SuppressWarnings("unused")
		Register registers = null;

		System.out.println(register.getUsername());
		HttpSession session = request.getSession(false);
		if (session == null || !request.isRequestedSessionIdValid()) {

		} else {
			register.setId(id);
			String password = register.getPassword();
			String encoders = bCryptPasswordEncoder.encode(password);
			register.setPassword(encoders);
			Date date = new Date();
			register.setModifyDate(date);
			@SuppressWarnings("unused")
			Register registers1 = service.updateProducts(register);
			HttpSession session1 = request.getSession();
			session1.setMaxInactiveInterval(300);
			String ss = session1.getId();
			SessionData user1 = sessionDao.findByUserName(ss);
			String sa = user1.getSessionid();
			if (ss.equals(sa)) {
				String username = user1.getUsername();
				register.setModifyBy(username);
				service.addUsers(register);
			} else {
			}

		}
		Register registers1 = null;
		registers = registers1;
		return registers1;
	}

//	____________________________________________7______________________________________________

	@GetMapping("/downloadsessiondata")
	public ResponseEntity<Resource> getFile() {
		String filename = "UsersSessionData.csv";
		InputStreamResource file = new InputStreamResource(sessionDataService.load());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

//	____________________________________________8______________________________________________

	@GetMapping("/adminAdvanceSearch")
	public List<Register> adminDataSearch(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "firstname") String firstname,
			@RequestParam(required = false, name = "lastname") String lastname,
			@RequestParam(required = false, name = "username") String username,
			@RequestParam(required = false, name = "createdBy") String createdBy,
			@RequestParam(required = false, name = "modifyBy") String modifyBy, HttpServletRequest request) {
		
		List<Register> listofAdmin = null;
		HttpSession session = request.getSession(false);
		if (session == null || !request.isRequestedSessionIdValid()) {

		} else {

			List<Register> adminList =  registerAdvanceSearchDao.searchAdminData(id, firstname, lastname, username, createdBy, modifyBy);
			listofAdmin = adminList;
		}
		return listofAdmin;
	}

//	____________________________________________9______________________________________________

	@PostMapping("/adminUploadFile")
	public ResponseEntity<?> adminFile(@RequestParam("file") MultipartFile file,
			@RequestParam("filename") String filename) {
		if (AdminRegisterHelper.checkExcelFormat(file)) {
			bulkDataUploadService.adminsave(file, filename);
			return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
	}

//	____________________________________________10______________________________________________

	@PostMapping("/destroy")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "Users Logout Successfully...";
	}
}

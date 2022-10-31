package com.PSL.management.employeeDataController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.PSL.management.bulkDataUpload.BulkDataUploadService;
import com.PSL.management.bulkDataUpload.PresentEmployeeHelper;
import com.PSL.management.employeeAttendenceAdvanceSearch.AttendenceAdvanceSearchDao;
import com.PSL.management.employeeModel.EmployeeAttendenceEntity;
import com.PSL.management.employeeService.EmployeeService;
import com.PSL.management.service.IRegisterService;

@RestController
@RequestMapping("/AttendenceManage")
public class EmployeeAttendenceController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private IRegisterService iRegisterService;

	@Autowired
	private AttendenceAdvanceSearchDao attendenceAdvanceSearchDao;
	
	@Autowired
	private BulkDataUploadService bulkDataUploadService;

//  Employee Attendance Insert	By EmployeeID
	@PostMapping("/EmployeeAttendence")
	public EmployeeAttendenceEntity employeeAttendence(@RequestBody EmployeeAttendenceEntity employeeAttendenceEntity) {
		EmployeeAttendenceEntity employeeAttendence = employeeService
				.addEmployeeAttendenceDetails(employeeAttendenceEntity);
		return employeeAttendence;
	}

	@GetMapping("{pageNo}/{pageSize}")
	public List<EmployeeAttendenceEntity> getAttendenceEmployeeData(@PathVariable int pageNo,
			@PathVariable int pageSize) {

//		List<EmployeeRegistrationEntityModel> allEmployeeList =  employeeService.EmployeeAllEmployeeList();
		return iRegisterService.findEmployeePresentPage(pageNo, pageSize);
	}

	@GetMapping("/attendenceEmployees")
	public List<EmployeeAttendenceEntity> employeesAttendence(
			@RequestParam(required = false, name = "employeeid") String employeeid,
			@RequestParam(required = false, name = "employeename") String employeename,
			@RequestParam(required = false, name = "attendencedate") String attendencedate,
			@RequestParam(required = false, name = "entrytime") String entrytime,
			@RequestParam(required = false, name = "exittime") String exittime) {

		return attendenceAdvanceSearchDao.employeeAttenceList(employeeid, employeename, attendencedate, entrytime,
				exittime);
	}

	@PostMapping("/uploadEmployeePresentFile")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam(name = "filename") String filename) {
		if (PresentEmployeeHelper.checkExcelFormat(file)) {
			this.bulkDataUploadService.presentEmployeeSave(file, filename);
			return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
	}
}

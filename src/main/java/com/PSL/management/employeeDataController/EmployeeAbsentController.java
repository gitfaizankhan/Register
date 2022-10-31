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
import com.PSL.management.absentDataAdvanceSearch.AbsentAdvanceSearchDao;
import com.PSL.management.bulkDataUpload.AbsentEmployeeHelper;
import com.PSL.management.bulkDataUpload.BulkDataUploadService;
import com.PSL.management.employeeModel.EmployeeAbsentDetails;
import com.PSL.management.employeeService.EmployeeService;
import com.PSL.management.service.IRegisterService;

@RestController
@RequestMapping("/AbsentManage")
public class EmployeeAbsentController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private IRegisterService iRegisterService;

	@Autowired
	private AbsentAdvanceSearchDao absentAdvanceSearchDao;

	@Autowired
	private BulkDataUploadService bulkDataUploadService;

//	_____________________________________1___________________________________________

	@PostMapping("/EmployeeAbsent")
	public EmployeeAbsentDetails employeeAbsent(@RequestBody EmployeeAbsentDetails employeeAbsentDetails) {
		EmployeeAbsentDetails employeeAbsent = employeeService.addEmployeeAbsentDetails(employeeAbsentDetails);
		return employeeAbsent;
	}

//	_____________________________________2___________________________________________

	@GetMapping("{pageNo}/{pageSize}")
	public List<EmployeeAbsentDetails> getAllEmployee(@PathVariable int pageNo, @PathVariable int pageSize) {
		return iRegisterService.findEmployeeAbsentPage(pageNo, pageSize);
	}

//	_____________________________________3___________________________________________
	
	@GetMapping("/absentEmployees")
	public List<EmployeeAbsentDetails> absentEmployee(
			@RequestParam(required = false, name = "employeeid") String employeeid,
			@RequestParam(required = false, name = "employeename") String employeename,
			@RequestParam(required = false, name = "leave") String leave,
			@RequestParam(required = false, name = "emloyeeabsentdate") String emloyeeabsentdate) {
		return absentAdvanceSearchDao.employeeAbsentList(employeeid, employeename, leave, emloyeeabsentdate);
	}

	
//	_____________________________________4___________________________________________
	
	@PostMapping("/uploadEmployeeAbsentFile")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam(name = "filename") String filename) {
		if (AbsentEmployeeHelper.checkExcelFormat(file)) {
			this.bulkDataUploadService.absentEmployeeSave(file, filename);
			return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
	}
}

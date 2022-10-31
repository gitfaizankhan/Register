package com.PSL.management.employeeDataController;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.PSL.management.bulkDataUpload.BulkDataUploadService;
import com.PSL.management.bulkDataUpload.RegisterEmployeesHelper;
import com.PSL.management.employeeDataAdvanceSearch.EmployeeManageDataAdvanceSearchDao;
import com.PSL.management.employeeModel.EmployeeRegistrationEntityModel;
import com.PSL.management.employeeModelRepository.EmployeeRegistrationModelRepository;
import com.PSL.management.employeeService.EmployeeService;
import com.PSL.management.service.IRegisterService;

@RestController
@RequestMapping("/Employee")
public class EmployeeManageController {

	@Autowired
	private EmployeeRegistrationModelRepository employeeRegistraionModelRepository;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private IRegisterService iRegisterService;
	
	@Autowired
	private BulkDataUploadService bulkDataUploadService;

	@Autowired
	private EmployeeManageDataAdvanceSearchDao employeeManageDataAdvanceSearchDao;

//	                        Register Employee
// ======================================================================================
	@PostMapping("/register")
	public EmployeeRegistrationEntityModel addEmployee(
			@RequestBody EmployeeRegistrationEntityModel employeeRegistrationEntityModel) throws IOException {

		EmployeeRegistrationEntityModel employeeRegistration = employeeRegistraionModelRepository
				.findByUsername(employeeRegistrationEntityModel.getEmployeeusername());
		System.out.println(employeeRegistrationEntityModel);
		if (employeeRegistration != null) {
			return employeeRegistration;
		} else {

		}
		System.out.println(employeeRegistrationEntityModel);
		FileInputStream img = new FileInputStream(employeeRegistrationEntityModel.getEmployeeimage());
		byte[] fileContent = img.readAllBytes();
		String encodedString = Base64.getEncoder().encodeToString(fileContent);
		employeeRegistrationEntityModel.setEmployeeimage(encodedString);
		EmployeeRegistrationEntityModel EmployeeRegistrationEntityModel = employeeService
				.addEmployee(employeeRegistrationEntityModel);
		return EmployeeRegistrationEntityModel;
	}

//	                         Get Employee Details
//	========================================================================================

	@GetMapping("/detailsAll")
	public List<EmployeeRegistrationEntityModel> getAllEmployee(@RequestParam(name = "pageNo") int pageNo, @RequestParam(name = "pageSize") int pageSize) {

//		List<EmployeeRegistrationEntityModel> allEmployeeList =  employeeService.EmployeeAllEmployeeList();
		return iRegisterService.findEmployeePagination(pageNo, pageSize);
	}

//	          Get Employee Details By ID
	@GetMapping("/details")
	public EmployeeRegistrationEntityModel getEmployeeFullDetailsByID(@RequestParam(name = "id") Long id) {
		EmployeeRegistrationEntityModel employeeRegistrationEntityModel = employeeService.EmployeeDetailsByID(id);
		return employeeRegistrationEntityModel;
	}

//	             UPDATE Employee Details By ID

	@PutMapping("/update")
	public EmployeeRegistrationEntityModel updateEmployeeDetailsById(
			@RequestBody EmployeeRegistrationEntityModel employeeRegistrationEntityModel, @RequestParam(name = "id") long id) {
		employeeRegistrationEntityModel.setId(id);
		EmployeeRegistrationEntityModel employeeRegistrationEntityModel1 = employeeService
				.updateEmployeeDetails(employeeRegistrationEntityModel);

		return employeeRegistrationEntityModel1;
	}

//	         Delete Employee By ID

	@DeleteMapping("/delete")
	public String deleteEmployeeId(@RequestParam(name = "id") long id) {
		employeeService.deleteEmployee(id);
		return "Employee Remove Successfully";
	}

//	 Advance SearchS

	@GetMapping("/search")
	public List<EmployeeRegistrationEntityModel> RegisteredEmployee(@RequestParam(required = false, name = "id") Long id,
			@RequestParam(required = false, name = "employeeid") String employeeid,
			@RequestParam(required = false, name = "employeefirstname") String employeefirstname,
			@RequestParam(required = false, name = "employeelastname") String employeelastname,
			@RequestParam(required = false, name = "employeeusername") String employeeusername,
			@RequestParam(required = false, name = "employeephonenumber") String employeephonenumber,
			@RequestParam(required = false, name = "employeemial") String employeemial,
			@RequestParam(required = false, name = "employeeaddress") String employeeaddress,
			@RequestParam(required = false, name = "employeejoindate") String employeejoindate,
			@RequestParam(required = false, name = "employeeregistrationdate") String employeeregistrationdate,
			@RequestParam(required = false, name = "employeeleavedate") String employeeleavedate){
		
		return employeeManageDataAdvanceSearchDao.findRegisteredEmployeeData(employeeid, employeefirstname,  employeelastname, employeeusername, employeephonenumber,
			employeemial, employeeaddress, employeejoindate, employeeregistrationdate, employeeleavedate);
	}
	
	
	@PostMapping("/uploadFile")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam(name = "filename") String filename) {
		if (RegisterEmployeesHelper.checkExcelFormat(file)) {
			this.bulkDataUploadService.registerEmployeesSave(file, filename);
			return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
	}

}

package com.PSL.management.bulkDataUpload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.PSL.management.employeeModel.EmployeeRegistrationEntityModel;


public class RegisterEmployeesHelper {

	
	public static boolean checkExcelFormat(MultipartFile file) {

		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}

	}

	public static List<EmployeeRegistrationEntityModel> convertExcelToListOfProduct(InputStream employeesFile, String filename) throws IOException {
		List<EmployeeRegistrationEntityModel> newEmployeeslist = new ArrayList<>();
		
		XSSFWorkbook newEmployeesWorkbook =  new XSSFWorkbook(employeesFile);

		XSSFSheet sheet = newEmployeesWorkbook.getSheet(filename);

		int rowNumber = 0;
		Iterator<Row> iterator = sheet.iterator();

		while (iterator.hasNext() == true) {
			Row row = iterator.next();

			if (rowNumber == 0) {
				rowNumber++;
				continue;
			}

			Iterator<Cell> cells = row.iterator();

			int cid = 0;

			EmployeeRegistrationEntityModel employeeRegistrationEntityModel = new EmployeeRegistrationEntityModel();

			while (cells.hasNext() == true) {
				Cell cell = cells.next();
				switch (cid) {
				case 0:
					employeeRegistrationEntityModel.setEmployeeid(cell.getStringCellValue());
					break;
				case 1:
					employeeRegistrationEntityModel.setEmployeefirstname(cell.getStringCellValue());
					break;
				case 2:
					employeeRegistrationEntityModel.setEmployeelastname(cell.getStringCellValue());
					break;
				case 3:
					employeeRegistrationEntityModel.setEmployeemial(cell.getStringCellValue());
					break;
				case 4:
					long phoneNumber = (long) cell.getNumericCellValue();
					String empPhone = String.valueOf(phoneNumber);
					employeeRegistrationEntityModel.setEmployeephonenumber(empPhone);
					break;
				case 5:
					employeeRegistrationEntityModel.setEmployeeaddress(cell.getStringCellValue());
					break;
				case 6:
					employeeRegistrationEntityModel.setEmployeeregistrationdate(cell.getDateCellValue());
					break;
				case 7:
					employeeRegistrationEntityModel.setEmployeejoindate(cell.getDateCellValue());
					break;
				case 8:
//					employeeRegistrationEntityModel.setEmployeeleavedate(cell.getDateCellValue());
					break;
				case 9:
					String employeeImg = cell.getStringCellValue();
					FileInputStream img = new FileInputStream(employeeImg);
					byte[] fileContent = img.readAllBytes();
					String encodedString = Base64.getEncoder().encodeToString(fileContent);
					employeeRegistrationEntityModel.setEmployeeimage(encodedString);
					break;
				default:
					break;
				}
				cid++;
			}
			newEmployeeslist.add(employeeRegistrationEntityModel);
		}
		return newEmployeeslist;
	}
}

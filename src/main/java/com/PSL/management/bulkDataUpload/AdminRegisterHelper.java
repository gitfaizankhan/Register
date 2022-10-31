package com.PSL.management.bulkDataUpload;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.PSL.management.dataModel.Register;

public class AdminRegisterHelper {
	
	public static boolean checkExcelFormat(MultipartFile file) {

		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}

	}

	public static List<Register> convertExcelToListOfProduct(InputStream adminFile, String filename) throws IOException {
		List<Register> adminlist = new ArrayList<>();
		
		XSSFWorkbook adminWorkbook =  new XSSFWorkbook(adminFile);

		XSSFSheet sheet = adminWorkbook.getSheet(filename);

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

			Register registerAdmin = new Register();

			while (cells.hasNext() == true) {
				Cell cell = cells.next();
				switch (cid) {
				case 0:
					registerAdmin.setFirstname(cell.getStringCellValue());
					break;
				case 1:
					registerAdmin.setLastname(cell.getStringCellValue());
					break;
				case 2:
					registerAdmin.setUsername(cell.getStringCellValue());
					break;
				case 3:
					String passValue = cell.getStringCellValue();
					String password = new BCryptPasswordEncoder().encode(passValue);
					registerAdmin.setPassword(password);
					break;
				case 4:
					registerAdmin.setCreatedBy(cell.getStringCellValue());
					break;
				case 5:
					registerAdmin.setCreatedDate(cell.getDateCellValue());
					break;
				case 6:
					registerAdmin.setModifyBy(cell.getStringCellValue());
					break;
				case 7:
					registerAdmin.setModifyDate(cell.getDateCellValue());
				default:
					break;
				}
				cid++;
			}
			adminlist.add(registerAdmin);
		}
		return adminlist;
	}
}

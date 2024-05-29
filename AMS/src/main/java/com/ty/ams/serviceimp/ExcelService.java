package com.ty.ams.serviceimp;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.ams.dao.UserDao;
import com.ty.ams.entity.Attendance;
import com.ty.ams.entity.TimeSheet;
import com.ty.ams.entity.User;
import com.ty.ams.service.TimeSheetService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ExcelService {

	@Autowired
	UserDao dao;
	@Autowired
	TimeSheetService sheetService;

	public void generateExcelForOneUserOfOneMonth(HttpServletResponse response, int userID) throws IOException {
		Optional<User> user = dao.findUserById(userID);
		if (user.isPresent()) {
			User user1 = user.get();
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet(user1.getName() + "timesheet");

			HSSFRow row1 = sheet.createRow(0);
			row1.createCell(0).setCellValue("Employee Name");
			row1.createCell(1).setCellValue(user1.getName());

			HSSFRow row2 = sheet.createRow(1);
			row2.createCell(0).setCellValue("Employee Id");
			row2.createCell(1).setCellValue(user1.getEmpId());

			HSSFRow row3 = sheet.createRow(2);
			row3.createCell(0).setCellValue("Attendance Period");
			row3.createCell(1).setCellValue("null");

			HSSFRow row6 = sheet.createRow(6);
			row6.createCell(0).setCellValue("Date");
			row6.createCell(1).setCellValue("Day");
			row6.createCell(2).setCellValue("LoginTime");
			row6.createCell(3).setCellValue("Logout Time");
			row6.createCell(4).setCellValue("Attendance");
			row6.createCell(5).setCellValue("TotalWorkingHours");
			row6.createCell(6).setCellValue("BatchCode1");
			row6.createCell(7).setCellValue("Timing");
			row6.createCell(8).setCellValue("BatchCode2");
			row6.createCell(9).setCellValue("Timing");
			row6.createCell(10).setCellValue("BatchCode3");
			row6.createCell(11).setCellValue("Timing");
			row6.createCell(12).setCellValue("BatchCode4");
			row6.createCell(13).setCellValue("Timing");
			Optional<TimeSheet> CurrentMonthtimesheet = sheetService.fetchCurrentMonthTimeSheetofUser(user1.getUserId())
					.getBody().getBody().stream().findFirst();
			List<Attendance> currentMonthAttendances = CurrentMonthtimesheet.get().getAttendences();
			int dataRow = 7;
			for (Attendance attendance : currentMonthAttendances) {
				HSSFRow dataRows = sheet.createRow(dataRow);
				dataRows.createCell(0).setCellValue(attendance.getLoginDateandTime().getDayOfMonth());
				dataRows.createCell(1).setCellValue(attendance.getLoginDateandTime().getDayOfWeek() + "");
				dataRows.createCell(2).setCellValue(attendance.getLoginDateandTime() + "");
				dataRows.createCell(3).setCellValue(attendance.getLogoutDateandTime() + "");
				dataRows.createCell(4).setCellValue(attendance.getAttendanceStatus() + "");
				dataRows.createCell(5).setCellValue(attendance.getTotalWorkingHours() + "");
//				List<Batch> batches = attendance.getBatchs();
				dataRow++;
			}
			ServletOutputStream sos = response.getOutputStream();
			book.write(sos);
			sos.close();
			book.close();
		}
	}

	public void getDefaultTimesheetOfUserInExcel(int timesheetId) {
		sheetService.findTimeSheetById(timesheetId);
	}

	// Working Method
//
//	public void generateExcel(HttpServletResponse response, int userId, int timesheetId) throws IOException {
//		HSSFWorkbook book = new HSSFWorkbook();
//		HSSFSheet sheet = book.createSheet(user.getName() + "timesheet");
//
//		HSSFRow row1 = sheet.createRow(0);
//		row1.createCell(0).setCellValue("Employee Name");
//		row1.createCell(1).setCellValue(user.getName());
//
//		HSSFRow row2 = sheet.createRow(1);
//		row2.createCell(0).setCellValue("Employee Id");
//		row2.createCell(1).setCellValue(user.getEmpId());
//
//		HSSFRow row3 = sheet.createRow(2);
//		row3.createCell(0).setCellValue("Attendance Period");
//		row3.createCell(1).setCellValue("null");
//
//		HSSFRow row6 = sheet.createRow(6);
//		row6.createCell(0).setCellValue("Date");
//		row6.createCell(1).setCellValue("Day");
//		row6.createCell(2).setCellValue("LoginTime");
//		row6.createCell(3).setCellValue("Logout Time");
//		row6.createCell(4).setCellValue("Attendance");
//		row6.createCell(5).setCellValue("TotalWorkingHours");
//		row6.createCell(6).setCellValue("BatchCode1");
//		row6.createCell(7).setCellValue("Timing");
//		row6.createCell(8).setCellValue("BatchCode2");
//		row6.createCell(9).setCellValue("Timing");
//		row6.createCell(10).setCellValue("BatchCode3");
//		row6.createCell(11).setCellValue("Timing");
//		row6.createCell(12).setCellValue("BatchCode4");
//		row6.createCell(13).setCellValue("Timing");
//		List<Attendance> attendances = sheetService.findTimeSheetById(timesheetId).getBody().getBody().getAttendences();
//		int dataRow = 7;
//		for (Attendance attendance : attendances) {
//			HSSFRow dataRows = sheet.createRow(dataRow);
//			dataRows.createCell(0).setCellValue(attendance.getDate());
//			dataRows.createCell(1).setCellValue(attendance.getDate().getDayOfWeek() + "");
//			dataRows.createCell(2).setCellValue(attendance.getLoginTime() + "");
//			dataRows.createCell(3).setCellValue(attendance.getLogoutTime() + "");
//			dataRows.createCell(4).setCellValue(attendance.getAttendanceStatus() + "");
//			dataRows.createCell(5).setCellValue(attendance.getTotalWorkingHours() + "");
//			List<Batch> batches = attendance.getBatchs();
//			dataRow++;
//		}
//		ServletOutputStream sos = response.getOutputStream();
//		book.write(sos);
//		sos.close();
//		book.close();
//	}

}

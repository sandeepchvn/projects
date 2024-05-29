package com.ty.ams.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ty.ams.entity.TimeSheet;
import com.ty.ams.responsestructure.ResponseStructure;

public interface TimeSheetService {
	public ResponseEntity<ResponseStructure<TimeSheet>> saveTimeSheet(TimeSheet timeSheet, int userId);

	public ResponseEntity<ResponseStructure<TimeSheet>> updateTimeSheet(TimeSheet timeSheet);

	public ResponseEntity<ResponseStructure<TimeSheet>> findTimeSheetById(int id);

	public ResponseEntity<ResponseStructure<String>> deleteTimeSheetById(int id, int userId);

	public ResponseEntity<ResponseStructure<List<TimeSheet>>> findTimeSheetOfUserOnCustomDates(String startMonth,
			int start_year, String endMonth, int end_year, int user_id);

	public ResponseEntity<ResponseStructure<List<TimeSheet>>> findTimeSheetOfAllOnCustomDates(String startMonth,
			int start_year, String endMonth, int end_year);

	public ResponseEntity<ResponseStructure<List<TimeSheet>>> fetchCurrentMonthTimeSheetOfAll();

	public ResponseEntity<ResponseStructure<List<TimeSheet>>> fetchCurrentMonthTimeSheetofUser(int userId);

	// admin
	

	public ResponseEntity<ResponseStructure<TimeSheet>> saveAdminTimeSheet(TimeSheet timeSheet, int userId);
}

package com.ty.ams.serviceimp;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ams.dao.TimeSheetDao;
import com.ty.ams.dao.UserDao;
import com.ty.ams.entity.TimeSheet;
import com.ty.ams.entity.User;
import com.ty.ams.exceptionclasses.timesheet.TimeSheetAlreadyExists;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.TimeSheetService;
import com.ty.ams.util.UserRole;

@Service
public class TimeSheetServiceImp implements TimeSheetService {

	@Autowired
	TimeSheetDao timeSheetDao;

	@Autowired
	UserDao userDao;

	@Override
	public ResponseEntity<ResponseStructure<TimeSheet>> saveTimeSheet(TimeSheet timeSheet, int userId) {
		List<User> users = userDao.findUserByRole(UserRole.ADMIN);
		Optional<User> admin = users.stream().findAny();
		Optional<User> user = userDao.findUserById(userId);
		ResponseStructure<TimeSheet> responseStructure = new ResponseStructure<>();
		try {
			if (user.get() != null) {
				List<TimeSheet> listOfTimeSheets = user.get().getTimeSheets();
				if (listOfTimeSheets != null && !listOfTimeSheets.isEmpty()) {
					Optional<TimeSheet> timesheet = listOfTimeSheets.stream()
							.filter(sheet -> (sheet.getStart_date().getMonthValue() == LocalDate.now().getMonthValue()
									&& sheet.getStart_date().getYear() == LocalDate.now().getYear()))
							.findAny();
					if (timesheet.isPresent()) {
						throw new TimeSheetAlreadyExists();
					}
					timeSheet.setStart_date(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
							admin.get().getTimeSheets().stream().findAny().get().getStart_date().getDayOfMonth()));
					timeSheet.setEnd_date(endDate(timeSheet, admin.get()));
					timeSheetDao.saveTimeSheet(timeSheet);
					user.get().getTimeSheets().add(timeSheet);
					userDao.saveUser(user.get());
				} else {
					timeSheet.setStart_date(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
							admin.get().getTimeSheets().stream().findAny().get().getStart_date().getDayOfMonth()));
					timeSheet.setEnd_date(endDate(timeSheet, admin.get()));
					timeSheetDao.saveTimeSheet(timeSheet);
					List<TimeSheet> sheets = new ArrayList<TimeSheet>();
					sheets.add(timeSheet);
					user.get().setTimeSheets(sheets);
					userDao.updateUser(user.get());
				}
				responseStructure.setBody(timeSheet);
				responseStructure.setMessage("time sheet created successfully");
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
			}
			responseStructure.setBody(null);
			responseStructure.setMessage("User Not Found");
			responseStructure.setStatusCode(HttpStatus.CONFLICT.value());
			return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
		} catch (Exception e) {
			e.printStackTrace();
			responseStructure.setBody(timeSheet);
			responseStructure.setMessage("time sheet was not created ");
			responseStructure.setStatusCode(HttpStatus.CONFLICT.value());
			return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
		}
	}

	public LocalDate endDate(TimeSheet timeSheet, User admin) {
		Optional<TimeSheet> adminTimeSheet = admin.getTimeSheets().stream().findAny();
		int month = timeSheet.getStart_date().getMonthValue();
		int year = timeSheet.getStart_date().getYear();
		int endDate = adminTimeSheet.get().getEnd_date().getDayOfMonth();
		DateTimeFormatter inputFormatter = null;
		if (month <= 9 && month >= 1 && endDate <= 9 && endDate >= 1) {
			month += 1;
			inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
		}

		else if (month <= 9 && month >= 1 && endDate >= 10 && endDate <= 31) {
			month += 1;
			inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
		}

		else if (month == 10 || month == 11 && endDate >= 10 && endDate <= 31) {
			month += 1;
			inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		} else if (month == 10 || month == 11 && endDate <= 9 && endDate >= 1) {
			month += 1;
			inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		} else if (month == 12 && endDate <= 9 && endDate >= 1) {
			month = 1;
			year += 1;
			inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		} else if (month == 12 && endDate >= 10 && endDate <= 31) {
			month = 1;
			year += 1;
			inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		}
		return LocalDate.parse(year + "-" + month + "-" + endDate, inputFormatter);
	}

	@Override
	public ResponseEntity<ResponseStructure<TimeSheet>> updateTimeSheet(TimeSheet timeSheet) {
		ResponseStructure<TimeSheet> responseStructure = new ResponseStructure<>();
		Optional<TimeSheet> optional = timeSheetDao.findBytimesheet_id(timeSheet.getTimesheetId());
		if (optional.isPresent()) {
			timeSheetDao.updateTimeSheet(optional.get());
			responseStructure.setBody(optional.get());
			responseStructure.setMessage("time sheet updated successfully ");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<TimeSheet>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setMessage("time sheet not found");
			responseStructure.setBody(null);
			responseStructure.setStatusCode(HttpStatus.CONFLICT.value());
			return new ResponseEntity<ResponseStructure<TimeSheet>>(responseStructure, HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<TimeSheet>> findTimeSheetById(int id) {
		ResponseStructure<TimeSheet> responseStructure = new ResponseStructure<>();
		Optional<TimeSheet> sheet = timeSheetDao.findBytimesheet_id(id);
		if (sheet.isPresent()) {
			responseStructure.setMessage("time sheet fetched successfully ");
			responseStructure.setBody(sheet.get());
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<TimeSheet>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setMessage("time sheet not found");
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setBody(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteTimeSheetById(int id, int userId) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		try {
			User user = userDao.findUserById(userId).get();
			user.getTimeSheets().remove(timeSheetDao.findBytimesheet_id(id).get());
			userDao.updateUser(user);
			timeSheetDao.deleteTimeSheetById(id);
		} catch (Exception e) {
			responseStructure.setMessage("time sheet not found");
			responseStructure.setBody("FAILED");
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
		responseStructure.setMessage("time sheet deleted successfully");
		responseStructure.setBody("SUCCESS");
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		return new ResponseEntity<>(responseStructure, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<TimeSheet>>> findTimeSheetOfUserOnCustomDates(String startMonth,
			int start_year, String endMonth, int end_year, int userId) {
		ResponseStructure<List<TimeSheet>> responseStructure = new ResponseStructure<>();
		try {
			User user = userDao.findUserById(userId).get();
			List<TimeSheet> timeSheets = user.getTimeSheets().stream()
					.filter(timesheet -> timesheet.getStart_date().getMonth().getValue() >= Month
							.valueOf(startMonth.toUpperCase()).getValue()
							&& timesheet.getStart_date().getMonth().getValue() <= Month.valueOf(endMonth.toUpperCase())
									.getValue()
							&& timesheet.getStart_date().getYear() >= start_year
							&& timesheet.getStart_date().getYear() <= end_year)
					.collect(Collectors.toList());
			responseStructure.setBody(timeSheets);
			responseStructure.setMessage(" FETCHED SUCCESSFULLY");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<TimeSheet>>>(responseStructure, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseStructure.setBody(new ArrayList<>());
			responseStructure.setMessage("FAILED TO FETCH");
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<ResponseStructure<List<TimeSheet>>> findTimeSheetOfAllOnCustomDates(String startMonth,
			int start_year, String endMonth, int end_year) {
		ResponseStructure<List<TimeSheet>> responseStructure = new ResponseStructure<>();
		try {
			List<TimeSheet> timeSheets = timeSheetDao.findAllTimeSheets().stream()
					.filter(timesheet -> timesheet.getStart_date().getMonth().getValue() >= Month
							.valueOf(startMonth.toUpperCase()).getValue()
							&& timesheet.getStart_date().getMonth().getValue() <= Month.valueOf(endMonth.toUpperCase())
									.getValue()
							&& timesheet.getStart_date().getYear() >= start_year
							&& timesheet.getStart_date().getYear() <= end_year)
					.collect(Collectors.toList());
			responseStructure.setBody(timeSheets);
			responseStructure.setMessage(" FETCHED SUCCESSFULLY");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<TimeSheet>>>(responseStructure, HttpStatus.OK);
		} catch (Exception e) {
			responseStructure.setBody(new ArrayList<>());
			responseStructure.setMessage("FAILED TO FETCH");
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<TimeSheet>>> fetchCurrentMonthTimeSheetofUser(int userId) {
		Optional<TimeSheet> optTimeSheet = null;
		LocalDate currentDate = LocalDate.now();
		ResponseStructure<List<TimeSheet>> responseStructure = new ResponseStructure<>();
		try {

			Optional<User> user = userDao.findUserById(userId);
			if (user.get() != null) {
				List<TimeSheet> timeSheets = user.get().getTimeSheets();
				optTimeSheet = timeSheets.stream()
						.filter(timeSheet -> (timeSheet.getStart_date().getMonth().equals(currentDate.getMonth())
								&& timeSheet.getStart_date().getYear() == currentDate.getYear()))
						.findFirst();
				responseStructure.setBody(Arrays.asList(optTimeSheet.get()));
				responseStructure.setMessage(" FETCHED SUCCESSFULLY");
				responseStructure.setStatusCode(HttpStatus.OK.value());
			}
			return new ResponseEntity<ResponseStructure<List<TimeSheet>>>(responseStructure, HttpStatus.OK);
		} catch (Exception e) {
			responseStructure.setBody(new ArrayList<>());
			responseStructure.setMessage("FAILED TO FETCH");
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<TimeSheet>>> fetchCurrentMonthTimeSheetOfAll() {
		Optional<TimeSheet> optTimeSheet = null;
		LocalDate currentDate = LocalDate.now();
		ResponseStructure<List<TimeSheet>> responseStructure = new ResponseStructure<>();
		try {
			List<TimeSheet> timeSheets = timeSheetDao.findAllTimeSheets().stream()
					.filter(timeSheet -> timeSheet.getStart_date().getMonth().getValue() == currentDate.getMonth()
							.getValue() && timeSheet.getStart_date().getYear() == currentDate.getYear())
					.collect(Collectors.toList());
			responseStructure.setBody(timeSheets);
			responseStructure.setMessage(" FETCHED SUCCESSFULLY");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<TimeSheet>>>(responseStructure, HttpStatus.OK);
		} catch (Exception e) {
			responseStructure.setBody(new ArrayList<>());
			responseStructure.setMessage("FAILED TO FETCH");
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<ResponseStructure<TimeSheet>> saveAdminTimeSheet(TimeSheet timesheet, int userId) {
		Optional<User> user = userDao.findUserById(userId);
		List<TimeSheet> sheets = user.get().getTimeSheets();
		ResponseStructure<List<TimeSheet>> responseStructure = new ResponseStructure<>();
		try {
			if (sheets == null || sheets.isEmpty()) {
				ArrayList<TimeSheet> new_sheets = new ArrayList<TimeSheet>();
				timeSheetDao.saveTimeSheet(timesheet);
				new_sheets.add(timesheet);
				user.get().setTimeSheets(new_sheets);
				userDao.updateUser(user.get());
			} else {
				TimeSheet sheet = sheets.stream().findAny().get();
				sheet.setStart_date(timesheet.getStart_date());
				sheet.setEnd_date(timesheet.getEnd_date());
				timeSheetDao.updateTimeSheet(sheet);
			}
			responseStructure.setBody(null);
			responseStructure.setMessage("time sheet created successfully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			responseStructure.setBody(null);
			responseStructure.setMessage("time sheet was not created ");
			responseStructure.setStatusCode(HttpStatus.CONFLICT.value());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

}
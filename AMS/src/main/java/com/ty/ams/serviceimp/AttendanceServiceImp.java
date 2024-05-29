package com.ty.ams.serviceimp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ams.dao.AttendanceDao;
import com.ty.ams.dao.TimeSheetDao;
import com.ty.ams.dao.UserDao;
import com.ty.ams.entity.Attendance;
import com.ty.ams.entity.TimeSheet;
import com.ty.ams.exceptionclasses.attendance.AttendanceNotFoundException;
import com.ty.ams.exceptionclasses.attendance.UnableToCreateAttendance;
import com.ty.ams.exceptionclasses.timesheet.TimeSheetDoesNotExist;
import com.ty.ams.exceptionclasses.user.AttendanceNotFoundWithTheEnterdDate;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.AttendanceService;
import com.ty.ams.service.TimeSheetService;
import com.ty.ams.util.AttendanceStatus;

@Service
public class AttendanceServiceImp implements AttendanceService {

	@Autowired
	private AttendanceDao dao;

	@Autowired
	private TimeSheetDao timeSheetDao;

	@Autowired
	private UserDao userDao;
	@Autowired
	TimeSheetService timeSheetService;

	@Override
	public ResponseEntity<ResponseStructure<Attendance>> saveAttendance(Attendance attendance, int userId) {
		System.out.println("Im Here...");
		Optional<TimeSheet> timeSheet = timeSheetDao.fetchCurrentMonthTimeSheetofUser(userId);
		if (timeSheet.isEmpty()) {
			timeSheet = Optional
					.ofNullable(timeSheetService.saveTimeSheet(new TimeSheet(), userId).getBody().getBody());
		}
		if (attendance != null && timeSheet.isPresent()) {
			TimeSheet ts = timeSheet.get();
			dao.saveAttendance(attendance);
			List<Attendance> attendances = null;
			try {
				attendances = ts.getAttendences();
				attendances.add(attendance);
			} catch (Exception e) {
				attendances = new ArrayList<>();
				attendances.add(attendance);
			}
			timeSheetDao.updateTimeSheet(ts);
			ResponseStructure<Attendance> response = new ResponseStructure<Attendance>();
			attendance = dao.saveAttendance(attendance);
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage(HttpStatus.CREATED.getReasonPhrase());
			response.setBody(attendance);
			return new ResponseEntity<ResponseStructure<Attendance>>(response, HttpStatus.CREATED);
		}
		throw new UnableToCreateAttendance();
	}

	@Override
	public ResponseEntity<ResponseStructure<Attendance>> findAttandanceById(int id) {
		if (dao.findById(id).get() != null) {
			ResponseStructure<Attendance> response = new ResponseStructure<Attendance>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage(HttpStatus.FOUND.getReasonPhrase());
			response.setBody(dao.findById(id).get());
			return new ResponseEntity<ResponseStructure<Attendance>>(response, HttpStatus.FOUND);
		}
		throw new AttendanceNotFoundException();
	}

	@Override
	public ResponseEntity<ResponseStructure<Attendance>> updateAttandance(Attendance attendance) {
		if (attendance == null)
			throw new AttendanceNotFoundException();
		dao.updateAttendance(attendance);
		ResponseStructure<Attendance> response = new ResponseStructure<Attendance>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage(HttpStatus.OK.getReasonPhrase());
		response.setBody(attendance);
		return new ResponseEntity<ResponseStructure<Attendance>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<Attendance>> deleteAttandance(int id) {

		Optional<Attendance> delAttendance = dao.findById(id);

		if (delAttendance.get() != null) {
			ResponseStructure<Attendance> response = new ResponseStructure<Attendance>();
			response.setStatusCode(HttpStatus.NO_CONTENT.value());
			response.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
//			response.setBody(delAttendance.get());
			dao.deleteAttendance(id);
			return new ResponseEntity<ResponseStructure<Attendance>>(response, HttpStatus.NO_CONTENT);
		}
		throw new AttendanceNotFoundException();
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendanceByAttendanceStatus(String status) {

		if (dao.findAllAttendanceByAttendanceStatus(AttendanceStatus.valueOf(status.toUpperCase())) != null) {
			ResponseStructure<List<Attendance>> response = new ResponseStructure<List<Attendance>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage(HttpStatus.OK.getReasonPhrase());
			response.setBody(dao.findAllAttendanceByAttendanceStatus(AttendanceStatus.valueOf(status.toUpperCase())));

			return new ResponseEntity<ResponseStructure<List<Attendance>>>(response, HttpStatus.OK);
		}
		throw new AttendanceNotFoundException();
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendenceByDate(LocalDateTime date) {

		if (dao.findAllAttendenceByDate(date) != null) {
			ResponseStructure<List<Attendance>> response = new ResponseStructure<List<Attendance>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage(HttpStatus.OK.getReasonPhrase());
			response.setBody(dao.findAllAttendenceByDate(date));
			return new ResponseEntity<ResponseStructure<List<Attendance>>>(response, HttpStatus.OK);
		}
		throw new AttendanceNotFoundWithTheEnterdDate();
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendanceByAttendanceStatusAndDate(String status,
			LocalDateTime date) {
		List<Attendance> attendances = dao
				.findAllAttendanceByAttendanceStatusAndDate(AttendanceStatus.valueOf(status.toUpperCase()), date);
		if (attendances != null && !attendances.isEmpty()) {
			ResponseStructure<List<Attendance>> response = new ResponseStructure<List<Attendance>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage(HttpStatus.OK.getReasonPhrase());
			response.setBody(attendances);
			return new ResponseEntity<ResponseStructure<List<Attendance>>>(response, HttpStatus.OK);
		}
		throw new AttendanceNotFoundWithTheEnterdDate();

	}

	@Override
	public ResponseEntity<ResponseStructure<List<Attendance>>> findAttendanceByTimeSheetId(int attendanceId) {

		TimeSheet timesheet = timeSheetDao.findBytimesheet_id(attendanceId).get();
		if (timesheet != null) {

			ResponseStructure<List<Attendance>> response = new ResponseStructure<List<Attendance>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage(HttpStatus.OK.getReasonPhrase());
			response.setBody(timesheet.getAttendences());
			return new ResponseEntity<ResponseStructure<List<Attendance>>>(response, HttpStatus.OK);
		}
		throw new TimeSheetDoesNotExist();
	}
}

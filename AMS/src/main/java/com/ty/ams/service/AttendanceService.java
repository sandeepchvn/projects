package com.ty.ams.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ams.entity.Attendance;
import com.ty.ams.responsestructure.ResponseStructure;
@Service
public interface AttendanceService {

	ResponseEntity<ResponseStructure<Attendance>> saveAttendance(Attendance attendance, int userId);
	
	ResponseEntity<ResponseStructure<Attendance>> findAttandanceById(int id);

	ResponseEntity<ResponseStructure<Attendance>> updateAttandance(Attendance attendance);

	ResponseEntity<ResponseStructure<Attendance>> deleteAttandance(int id);

//	ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendanceByAttendanceStatus (AttendanceStatus status) ;

//	ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendenceByDate(LocalDate date);

//	ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendanceByAttendanceStatusAndDate(String status,LocalDate date);

	ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendanceByAttendanceStatus(String status);

	ResponseEntity<ResponseStructure<List<Attendance>>> findAttendanceByTimeSheetId(int attendanceId);

	ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendanceByAttendanceStatusAndDate(String status,
			LocalDateTime date);

	ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendenceByDate(LocalDateTime date);
}

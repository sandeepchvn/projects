package com.ty.ams.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ty.ams.exceptionclasses.attendance.AttendanceNotFoundException;
import com.ty.ams.exceptionclasses.attendance.UnableToCreateAttendance;
import com.ty.ams.exceptionclasses.timesheet.TimeSheetDoesNotExist;
import com.ty.ams.responsestructure.ResponseStructure;

@ControllerAdvice
public class AttendanceExceptionHandler {
	
	@ExceptionHandler(UnableToCreateAttendance.class)
	public ResponseEntity<ResponseStructure<String>> unableToCreateAttendance(
			UnableToCreateAttendance unableToCreateAttendance){
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.CONFLICT.value());
		structure.setMessage("Unable to create the Attendance Check The Details And TimeSheet ID");
		structure.setBody(unableToCreateAttendance.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(AttendanceNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> attendanceNotFoundException(
			AttendanceNotFoundException attendanceNotFoundException ){
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.NO_CONTENT.value());
		structure.setMessage("Unable to find the attendance with mention details...");
		structure.setBody(attendanceNotFoundException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(TimeSheetDoesNotExist.class)
	public ResponseEntity<ResponseStructure<String>> attendanceNotFoundException(
			TimeSheetDoesNotExist timeSheetDoesNotExist ){
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.NO_CONTENT.value());
		structure.setMessage("Unable to find the Time Sheet with Mentiond ID");
		structure.setBody(timeSheetDoesNotExist.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.NO_CONTENT);
	}

}

package com.ty.ams.exceptionclasses.attendance;

public class AttendanceNotFoundException extends RuntimeException{
	
	@Override
	public String getMessage() {
		return "The Attendance You are looking for is not found";
	}

}

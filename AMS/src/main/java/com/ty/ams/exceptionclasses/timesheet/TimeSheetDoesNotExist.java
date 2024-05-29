package com.ty.ams.exceptionclasses.timesheet;

public class TimeSheetDoesNotExist extends RuntimeException {
	
	@Override
	public String getMessage() {
		return "time sheet does not exist for the mention id";
	}

}

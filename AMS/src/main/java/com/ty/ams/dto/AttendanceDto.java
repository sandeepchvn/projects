//package com.ty.ams.dto;
//
//import com.ty.ams.entity.Attendance;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class AttendanceDto {
//	private int userId;
//	private String loginDateAndTime;
//	private String loginLocation;
//	private String logoutDateAndTime;
//	private String logoutLocation;
//	
//	public AttendanceDto(String loginDateAndTime, String loginLocation) {
//		this.loginDateAndTime = loginDateAndTime;
//		this.loginLocation = loginLocation;
//	}
//	
//	public AttendanceDto(int userId, String logoutDateAndTme, String logoutLocation) {
//		this.userId = userId;
//		this.logoutDateAndTime = logoutDateAndTme;
//		this.logoutLocation = logoutLocation;
//	}
//	
//	public static Attendance setToAttendanceFormat(AttendanceDto dto) {
//		Attendance attendance = new Attendance();
//		return attendance;
//	}
//	
//	public static Attendance getAsAttendanceFormat(AttendanceDto dto) {
//		Attendance attendance = new Attendance();
//		return attendance;
//	}
//	
//}

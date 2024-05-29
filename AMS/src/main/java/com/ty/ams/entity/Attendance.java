package com.ty.ams.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.ty.ams.util.AttendanceStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int attendanceId;
	private LocalDateTime loginDateandTime;
	private LocalDateTime logoutDateandTime;
	private String location;
	private String landMark;
	private String city;
	private String state;
	private String country;
	@Enumerated(EnumType.STRING)
	private AttendanceStatus attendanceStatus;
	private double totalWorkingHours;
	@ManyToMany
	private List<Batch> batchs;
	
	
//	private LocalDate date;
//	private LocalTime loginTime;
//	private LocalTime logoutTime;
//	private String loginLocation;
//	private String logoutLocation;
}
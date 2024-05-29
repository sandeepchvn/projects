package com.ty.ams.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ty.ams.entity.Attendance;
import com.ty.ams.util.AttendanceStatus;
@Repository
public interface AttendanceDao {

	public Attendance saveAttendance(Attendance attendance);

	public Optional<Attendance> findById(int id);

	public Attendance updateAttendance(Attendance attendance);

	public void deleteAttendance(int id);

	public List<Attendance> findAllAttendanceByAttendanceStatus(AttendanceStatus status);


	List<Attendance> findAllAttendenceByDate(LocalDateTime date);

	List<Attendance> findAllAttendanceByAttendanceStatusAndDate(AttendanceStatus status, LocalDateTime date);

}

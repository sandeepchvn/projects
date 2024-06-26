package com.ty.ams.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ams.entity.Attendance;
import com.ty.ams.util.AttendanceStatus;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

//	@Query("select a from Attendance a where a.attendanceStatus = ?1")
	List<Attendance> findByAttendanceStatus(AttendanceStatus attendanceStatus);

//	@Query("select a from Attendance a where a.date = ?1")
	List<Attendance> findByLoginDateandTime(LocalDateTime date);

//	@Query("select a from Attendance a where a.attendanceStatus = ?1 and a.date = ?2")
	List<Attendance> findByAttendanceStatusAndLoginDateandTime(AttendanceStatus status, LocalDateTime date);
}

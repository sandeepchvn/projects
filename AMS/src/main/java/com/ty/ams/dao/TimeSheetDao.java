package com.ty.ams.dao;

import java.util.List;
import java.util.Optional;

import com.ty.ams.entity.TimeSheet;

public interface TimeSheetDao {
	TimeSheet saveTimeSheet(TimeSheet timeSheet);

	TimeSheet updateTimeSheet(TimeSheet timeSheet);

	void deleteTimeSheetById(int id);

	List<TimeSheet> findAllTimeSheets();

	List<TimeSheet> findAllTimeSheetByUserId(int userId);

	public Optional<TimeSheet> fetchCurrentMonthTimeSheetofUser(int userId);

	public Optional<TimeSheet> findBytimesheet_id(int id);
}

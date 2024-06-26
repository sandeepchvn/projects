package com.ty.ams.daoimp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ams.dao.TimeSheetDao;
import com.ty.ams.dao.UserDao;
import com.ty.ams.entity.TimeSheet;
import com.ty.ams.entity.User;
import com.ty.ams.repository.TimeSheetRepository;

@Repository
public class TimeSheetDaoImp implements TimeSheetDao {

	@Autowired
	TimeSheetRepository timeSheetRepository;

	@Autowired
	UserDao userDao;

	@Override
	public TimeSheet saveTimeSheet(TimeSheet timeSheet) {
		return timeSheetRepository.save(timeSheet);
	}

	@Override
	public TimeSheet updateTimeSheet(TimeSheet timeSheet) {
		return timeSheetRepository.save(timeSheet);
	}

	@Override
	public void deleteTimeSheetById(int id) {
		timeSheetRepository.deleteById(id);
	}

	@Override
	public List<TimeSheet> findAllTimeSheets() {
		return timeSheetRepository.findAll();
	}

	@Override
	public List<TimeSheet> findAllTimeSheetByUserId(int userId) {
		return userDao.findUserById(userId).get().getTimeSheets();
	}

	public Optional<TimeSheet> findBytimesheet_id(int id) {
		return timeSheetRepository.findBytimesheet_id(id);
	}

	public Optional<TimeSheet> fetchCurrentMonthTimeSheetofUser(int userId) {
		Optional<User> user = userDao.findUserById(userId);
		return user.get().getTimeSheets().stream()
				.filter(timeSheet -> timeSheet.getStart_date().getMonth() == LocalDate.now().getMonth()
						&& timeSheet.getStart_date().getYear() == LocalDate.now().getYear())
				.findAny();
	}
}

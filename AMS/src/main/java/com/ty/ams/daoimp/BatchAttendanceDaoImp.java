package com.ty.ams.daoimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ams.dao.BatchAttendanceDao;
import com.ty.ams.entity.BatchAttendance;
import com.ty.ams.repository.BatchAttendanceRepository;
@Repository
public class BatchAttendanceDaoImp implements BatchAttendanceDao{
	@Autowired
	private BatchAttendanceRepository attendanceRepository;
	
	@Override
	public BatchAttendance saveBatchAttendance(BatchAttendance batchAttendance) {
		
		return attendanceRepository.save(batchAttendance);
	}
	
	public int findBatchAttendanceCountByBatchId(int batch_id) {
		return attendanceRepository.findBatchAttendanceCountByBatchId(batch_id);
	}
	
	
	
	
	

	
	
	




}

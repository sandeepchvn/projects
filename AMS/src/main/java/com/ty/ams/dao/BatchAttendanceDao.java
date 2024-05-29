package com.ty.ams.dao;


import com.ty.ams.entity.BatchAttendance;

public interface BatchAttendanceDao {
	
	BatchAttendance saveBatchAttendance(BatchAttendance batchAttendance);
	public int findBatchAttendanceCountByBatchId(int batch_id);
	
	
}

package com.ty.ams.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ty.ams.entity.BatchAttendance;
import com.ty.ams.responsestructure.ResponseStructure;

public interface BatchAttendanceService {
	ResponseEntity<ResponseStructure<BatchAttendance>> saveBatchAttendance(
			int batch_id, MultipartFile file,int numberOfStudents) throws IOException;

	int findBatchAttendanceCountByBatchId(int batch_id);

}

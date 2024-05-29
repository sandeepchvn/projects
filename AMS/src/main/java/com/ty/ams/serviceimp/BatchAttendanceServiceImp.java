package com.ty.ams.serviceimp;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ty.ams.dao.BatchAttendanceDao;
import com.ty.ams.dao.BatchDao;
import com.ty.ams.entity.Batch;
import com.ty.ams.entity.BatchAttendance;
import com.ty.ams.entity.Image;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.BatchAttendanceService;

@Service
public class BatchAttendanceServiceImp implements BatchAttendanceService {
	@Autowired
	private BatchAttendanceDao batchAttendanceDao;

	@Autowired
	private BatchDao batchDao;

	public ResponseEntity<ResponseStructure<BatchAttendance>> saveBatchAttendance(
			int batch_id, MultipartFile file, int numberOfStudents) throws IOException {
		
		Optional<Batch> batch = batchDao.findBatchById(batch_id);

		ResponseStructure<BatchAttendance> responseStructure = null;
		if (batch.isPresent() && findBatchAttendanceCountByBatchId(batch_id) < 5) {
			BatchAttendance batchAttendance=new BatchAttendance();
			batchAttendance.setBatch(batch.get());
			batchAttendance.setNumOfStudents(numberOfStudents);
			Image image = new Image();
			image.setImage(file.getBytes());
			batchAttendance.setImages(image);
			batchAttendanceDao.saveBatchAttendance(batchAttendance);

			responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setBody(batchAttendance);
		}
		return new ResponseEntity<ResponseStructure<BatchAttendance>>(responseStructure, HttpStatus.OK);
	}
	

	public int findBatchAttendanceCountByBatchId(int batch_id) {
		return batchAttendanceDao.findBatchAttendanceCountByBatchId(batch_id);
	}



}

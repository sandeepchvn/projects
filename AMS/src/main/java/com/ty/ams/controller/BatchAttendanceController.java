package com.ty.ams.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ty.ams.entity.BatchAttendance;
import com.ty.ams.repository.BatchAttendanceRepository;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.serviceimp.BatchAttendanceServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/batchattendance")
public class BatchAttendanceController {
	@Autowired
	private BatchAttendanceServiceImp batchAttendanceServiceImp;

	@Autowired
	private BatchAttendanceRepository attendanceRepository;

	@Operation(description = "BatchAttendance Object Will be Saved...", summary = "To Save BatchAttendance Object to Database...")
	@ApiResponses(value = { @ApiResponse(description = "BatchAttendance Saved Successfully", responseCode = "201"),
			@ApiResponse(description = "Unable To Save BatchAttendance To Database", responseCode = "409") })
	@PostMapping("/{batch_id}/{numberOfStudents}")
	public ResponseEntity<ResponseStructure<BatchAttendance>> saveBatchAttendance(
			@PathVariable int batch_id,
			@RequestParam("image") MultipartFile file,@PathVariable int numberOfStudents) throws IOException {
		return batchAttendanceServiceImp.saveBatchAttendance( batch_id, file,numberOfStudents);
	}

	@GetMapping("/{id}")
	public int getCount(@PathVariable int id) {
		return attendanceRepository.findBatchAttendanceCountByBatchId(id);
	}

}

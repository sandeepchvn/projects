package com.ty.ams.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.ty.ams.exceptionclasses.user.ImageNotFound;
import com.ty.ams.responsestructure.ResponseStructure;

public class ImageExceptionHandler {
	@ExceptionHandler(ImageNotFound.class)
	public ResponseEntity<ResponseStructure<String>> unableToCreateAttendance(ImageNotFound imageNotFound){
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.CONFLICT.value());
		structure.setMessage("image not found");
		structure.setBody(imageNotFound.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.CONFLICT);
		
	}
}

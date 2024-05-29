package com.ty.ams.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ams.serviceimp.ExcelService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ExcelSheetController {
	@Autowired
	ExcelService excelService;

	@GetMapping("/excel/{userId}")
	public ResponseEntity generateExcelForOneUserOfOneMonth(HttpServletResponse response, @PathVariable int userId)
			throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "content-Disposition";
		String headerValue = "attachment;filename=pavan.xls";
		response.setHeader(headerKey, headerValue);
		excelService.generateExcelForOneUserOfOneMonth(response, userId);
		return new ResponseEntity(HttpStatus.OK).ok().build();
	}
}

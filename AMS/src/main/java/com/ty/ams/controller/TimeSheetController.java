package com.ty.ams.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.ty.ams.entity.TimeSheet;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.TimeSheetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("timesheet")
public class TimeSheetController {

	@Autowired
	TimeSheetService timeSheetService;

	@Autowired
	ExcelSheetController excelSheetController;

	@Operation(description = "timesheet Object Will be Saved...", summary = "To Save timesheet Object to Database...")
	@ApiResponses(value = { @ApiResponse(description = "timesheet Saved Successfully", responseCode = "201"),
			@ApiResponse(description = "Unable To Save timesheet To Database", responseCode = "409") })
	@PostMapping("/{userId}")
	public ResponseEntity<ResponseStructure<TimeSheet>> saveTimeSheet(TimeSheet timeSheet, @PathVariable int userId) {
		return timeSheetService.saveTimeSheet(timeSheet, userId);
	}

	@Operation(description = "admin timesheet Object Will be Saved...", summary = "To Save admin timesheet Object to Database...")
	@ApiResponses(value = { @ApiResponse(description = " admin timesheet Saved Successfully", responseCode = "201"),
			@ApiResponse(description = "Unable To Save admin timesheet To Database", responseCode = "409") })
	@PostMapping("/admin/{userId}")
	public ResponseEntity<ResponseStructure<TimeSheet>> saveAdminTimeSheet(@RequestBody TimeSheet timeSheet,
			@PathVariable int userId) {
		return timeSheetService.saveAdminTimeSheet(timeSheet, userId);

	}

	@Operation(description = "timesheet Object Will be Updated...", summary = "To Update timesheet Object...")
	@ApiResponses(value = { @ApiResponse(description = "timesheet Updated Successfully", responseCode = "200"),
			@ApiResponse(description = "Unable To Updated timesheet To Database", responseCode = "409") })
	@PutMapping
	public ResponseEntity<ResponseStructure<TimeSheet>> updateTimeSheet(@RequestBody TimeSheet timeSheet) {
		return timeSheetService.updateTimeSheet(timeSheet);
	}

	@Operation(description = "timesheet Object Will be deleted...", summary = "To delete timesheet Object...")
	@ApiResponses(value = { @ApiResponse(description = "timesheet delete Successfully", responseCode = "204 "),
			@ApiResponse(description = "Unable To Updated timesheet To Database", responseCode = "404") })
	@DeleteMapping("/{id}/{userId}")
	public ResponseEntity<ResponseStructure<String>> deleteTimeSheet(@PathVariable int id, @PathVariable int userId) {
		return timeSheetService.deleteTimeSheetById(id, userId);
	}

	@Operation(description = "Fetching / Find timesheet by timesheetId", summary = "To Find timesheet Object By timesheetId...")
	@ApiResponses(value = { @ApiResponse(description = "timesheet Found Successfully", responseCode = "200"),
			@ApiResponse(description = "Unable To Find timesheet for Provided timesheetId...", responseCode = "404") })
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<TimeSheet>> findTimeSheetById(@PathVariable int id) {
		return timeSheetService.findTimeSheetById(id);
	}

	@Operation(description = "Retrieve time sheets of all users within a custom date range", summary = "Get time sheets for a specified date range")
	@ApiResponses(value = { @ApiResponse(description = "timesheet Found Successfully", responseCode = "200"),
			@ApiResponse(description = "Unable To Find timesheets...", responseCode = "404") })
	@GetMapping("{startMonth}/{start_year}/{endMonth}/{end_year}")
	public ResponseEntity<ResponseStructure<List<TimeSheet>>> findTimeSheetOfAllOnCustomDates(
			@PathVariable String startMonth, @PathVariable int start_year, @PathVariable String endMonth,
			@PathVariable int end_year) {
		return timeSheetService.findTimeSheetOfAllOnCustomDates(startMonth, start_year, endMonth, end_year);
	}

	@Operation(description = "Retrieve time sheets of user within a custom date range", summary = "Get time sheets for a specified date range")
	@ApiResponses(value = { @ApiResponse(description = "timesheet Found Successfully", responseCode = "200"),
			@ApiResponse(description = "Unable To Find timesheet for Provided user...", responseCode = "404") })
	@GetMapping("{startMonth}/{start_year}/{endMonth}/{end_year}/{userId}")
	public ResponseEntity<ResponseStructure<List<TimeSheet>>> findTimeSheetOfUserOnCustomDates(
			@PathVariable String startMonth, @PathVariable int start_year, @PathVariable String endMonth,
			@PathVariable int end_year, @PathVariable int userId) {
		return timeSheetService.findTimeSheetOfUserOnCustomDates(startMonth, start_year, endMonth, end_year, userId);
	}

	@Operation(description = "Retrieve time sheets for the current month of all users", summary = " Get time sheets for the current ongoing month")
	@ApiResponses(value = { @ApiResponse(description = "timesheet Found Successfully", responseCode = "200"),
			@ApiResponse(description = "Unable To Find timesheet for Provided user...", responseCode = "404") })
	@GetMapping("currentAll")
	public ResponseEntity<ResponseStructure<List<TimeSheet>>> fetchCurrentMonthTimeSheetOfAllUsers() {
		return timeSheetService.fetchCurrentMonthTimeSheetOfAll();
	}

	@Operation(description = "Retrieve time sheets for the current month of user", summary = " Get time sheets for the current ongoing month")
	@ApiResponses(value = { @ApiResponse(description = "timesheet Found Successfully", responseCode = "200"),
			@ApiResponse(description = "Unable To Find timesheet for Provided user...", responseCode = "404") })
	@GetMapping("currentUser/{userId}")
	public ResponseEntity<ResponseStructure<List<TimeSheet>>> fetchCurrentMonthTimeSheetofUser(
			@PathVariable int userId) {
		return timeSheetService.fetchCurrentMonthTimeSheetofUser(userId);
	}

//	@GetMapping("currentUser/v2/{userId}")
//	public EntityModel<TimeSheet> fetchCurrentMonthTimeSheetofUserHat(@PathVariable int userId,
//			HttpServletResponse response) throws IOException {
//		TimeSheet timesheet = timeSheetService.fetchCurrentMonthTimeSheetofUser(userId).getBody().getBody().stream()
//				.findFirst().orElse(null);
//		EntityModel<TimeSheet> model = EntityModel.of(timesheet);
//		WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
//				.methodOn(excelSheetController.getClass()).generateExcelForOneUserOfOneMonth(response, userId));
//		String excelUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/excel/{userId}")
//				.buildAndExpand(userId).toUri().toString();
//		model.add(Link.of(excelUri).withRel("links"));
//		return model;
//	}

	@GetMapping("currentUser/v3/{userId}")
	public EntityModel<TimeSheet> fetchCurrentMonthTimeSheetofUserHat1(@PathVariable int userId,
			HttpServletResponse response) throws IOException {
		TimeSheet timesheet = timeSheetService.fetchCurrentMonthTimeSheetofUser(userId).getBody().getBody().stream()
				.findFirst().orElse(null);
		String excelUrl = "http://localhost:8080/excel/" + userId;
		EntityModel<TimeSheet> model = EntityModel.of(timesheet);
		model.add(Link.of(excelUrl).withRel("convert timesheet data into excel"));
		return model;
	}
}

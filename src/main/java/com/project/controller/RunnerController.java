package com.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dao.EmployeeSalaryDao;
import com.project.dao.ResponseDto;
import com.project.entity.EmployeeEntity;
import com.project.service.EmployeeService;
import com.project.service.SalaryService;

@RestController
@RequestMapping("/webpage")
public class RunnerController {

	private EmployeeService employeeService;

	private SalaryService salaryService;

	public RunnerController(EmployeeService employeeService, SalaryService salaryService) {
		super();
		this.employeeService = employeeService;
		this.salaryService = salaryService;
	}

	@PostMapping()
	private EmployeeSalaryDao createEmployee(@RequestBody EmployeeSalaryDao dao) {

		// return employeeService.createEmployee(dao);

		return employeeService.createEmployeeBuilder(dao);

	}

	@GetMapping("{id}")
	private ResponseDto retrieveById(@PathVariable("id") int id) {

		return employeeService.retrieveById(id);

	}

	@GetMapping("/attendancepercentage/{id}")
	private int retrieveAttendancePercentage(@PathVariable("id") int id) {

		return employeeService.retrieveAttendancePercentage(id);

	}

	@PutMapping("{id}")
	private ResponseDto updateLeave(@RequestBody EmployeeEntity dao,

			@PathVariable("id") int id) {

		//employeeService.updateLeave(dao, id);
 return employeeService.updateLeave(dao, id);

	}

	@GetMapping("/list/{status}")
	private List<ResponseDto> terminatedEmployeesByQuery(@PathVariable("status") String status) {

		return employeeService.terminatedEmployeesByQuery(status);

	}

	@PutMapping("/statusupdate/{id}")
	private ResponseDto updateEmployeeStatus(@PathVariable("id") int id) {

		return employeeService.updateEmployeeStatus(id);

	}

	@DeleteMapping("/deletedList")
	private HttpStatus deleteTerminatedEmployees() {

		return employeeService.deleteTerminatedEmployee();

	}

}

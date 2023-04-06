package com.project.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.project.dao.EmployeeSalaryDao;
import com.project.dao.ResponseDto;
import com.project.entity.EmployeeEntity;

public interface EmployeeService {

	EmployeeSalaryDao createEmployeeBuilder(EmployeeSalaryDao dao);

	ResponseDto retrieveById(int id);

	int retrieveAttendancePercentage(int id);

	ResponseDto updateLeave(EmployeeEntity entity, int id);

	List<ResponseDto> terminatedEmployeesByQuery(String status);

	ResponseDto updateEmployeeStatus(int id);

	HttpStatus deleteTerminatedEmployee();

	// unused

	EmployeeSalaryDao createEmployee(EmployeeSalaryDao dao);

	List<EmployeeEntity> terminatedEmployees(String status);

}

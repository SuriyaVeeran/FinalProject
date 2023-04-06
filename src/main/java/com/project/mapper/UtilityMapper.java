package com.project.mapper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.project.dao.EmployeeSalaryDao;
import com.project.dao.ResponseDto;
import com.project.entity.EmployeeEntity;

public class UtilityMapper {
	

	public static ResponseDto usingResponseDto(EmployeeEntity entity) {

		LocalDate currentDate = LocalDate.now();

		long overallDays = ChronoUnit.DAYS.between
				(entity.getDateofjoining(), currentDate);

		int daysworked = (int) overallDays - entity.getNumberOfLeave();

		ResponseDto dto = ResponseDto.builder()
				//.id(entity.getId()).
				.name(entity.getName()).status(entity.getStatus())
				.numberOfLeave(entity.getNumberOfLeave()).basicPay(entity.getSalary().getBasicPay())
				.daysWorked(daysworked).build();

		dto.setId(entity.getId());
		
		return dto;

	}
	
	public static EmployeeEntity requestdaoToEntityBuilder(EmployeeSalaryDao dao) {

		EmployeeEntity employeeEntityBuild = EmployeeEntity.builder().name(dao.getName()).dob(dao.getDob())
				.dateofjoining(dao.getDateofjoining()).status(dao.getStatus()).numberOfLeave(dao.getNumberOfLeave())
				.salary(dao.getSalary()).build();

		return employeeEntityBuild;

	}

}

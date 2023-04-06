package com.project.dao;

import java.time.LocalDate;

import com.project.entity.SalaryEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeSalaryDao {

	private int id;

	private String name;

	private String status;

	private LocalDate dob;

	private LocalDate dateofjoining;

	private int numberOfLeave;

	private SalaryEntity salary;

}

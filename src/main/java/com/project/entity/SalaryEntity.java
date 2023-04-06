package com.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class SalaryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int salaryid;

	@Column
	private int basicPay;

	@Column
	private int hra;

	@Column
	private int da;

	@Column
	private int misc;

	@OneToOne(mappedBy = "salary")
	private EmployeeEntity employee;

}

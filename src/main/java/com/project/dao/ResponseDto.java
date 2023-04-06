package com.project.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

	private int id;

	private String name;

	private String status;

	private int daysWorked;

	private int basicPay;
	
	private int numberOfLeave;

}

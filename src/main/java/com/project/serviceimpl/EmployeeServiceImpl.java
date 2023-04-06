package com.project.serviceimpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.customexpection.ResourceNotFoundException;
import com.project.dao.EmployeeSalaryDao;
import com.project.dao.ResponseDto;
import com.project.entity.EmployeeEntity;
import com.project.entity.SalaryEntity;
import com.project.mapper.UtilityMapper;
import com.project.repo.EmployeeRepository;
import com.project.repo.SalaryRepository;
import com.project.service.EmployeeService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	public EmployeeServiceImpl(EmployeeRepository employeerepo, SalaryRepository salaryrepo,
			EntityManager entitymanager) {
		super();
		this.employeerepo = employeerepo;
		this.salaryrepo = salaryrepo;
		this.entitymanager = entitymanager;

	}

	private EmployeeRepository employeerepo;

	private SalaryRepository salaryrepo;

	private EntityManager entitymanager;

	// pass the value from URL

	@Override
	public EmployeeSalaryDao createEmployeeBuilder(EmployeeSalaryDao dao) {

		EmployeeEntity entity = requestdaoToEntityBuilder(dao);

		employeerepo.save(entity);

		// dao.setId(entity.getId());

		return dao;
	}

	@Override
	public ResponseDto retrieveById(int id) {

		EmployeeEntity employeeEntity = employeerepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

		// ResponseDto responseDto = usingResponseDto(employeeEntity);

		ResponseDto responseDto = UtilityMapper.usingResponseDto(employeeEntity);

		return responseDto;

	}

	@Override
	public int retrieveAttendancePercentage(int id) {

		EmployeeEntity entity = employeerepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("percentage not obtained :" + id));

		LocalDate currentDate = LocalDate.now(); // 95 days

		long overall = ChronoUnit.DAYS.between(entity.getDateofjoining(), currentDate);

		int overallDays = (int) overall;

		int numberOfLeave = entity.getNumberOfLeave(); // --> 1

		int presentDays = overallDays - numberOfLeave; // 95 - 1 = 94

		// (93/94) ) * 100

		float attendance = ((float) presentDays / overallDays) * 100;

		return (int) attendance;
	}

	@Override
	public ResponseDto updateLeave(EmployeeEntity entity, int id) {

		EmployeeEntity empEntity = employeerepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

		empEntity.setNumberOfLeave(empEntity.getNumberOfLeave() + 1);

		ResponseDto responseDto = UtilityMapper.usingResponseDto(empEntity);

		// responseDto.setId(entity.getId());

		// EmployeeEntity existingEntity =
		employeerepo.save(empEntity);

	//	BeanUtils.copyProperties(entity, responseDto);

		return responseDto;

		// return void;

	}

	@Override
	public List<ResponseDto> terminatedEmployeesByQuery(String status) {

		// by query we will retrieve the status

		List<EmployeeEntity> list = employeerepo.findByEmploymentStatusByQuerySample(status);

		List<ResponseDto> dto = new ArrayList<>();

		for (EmployeeEntity value : list) {

			ResponseDto responseDto = UtilityMapper.usingResponseDto(value);

			dto.add(responseDto);

		}

		return dto;
	}

	@Override
	public ResponseDto updateEmployeeStatus(int id) {

		EmployeeEntity entity = employeerepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

		if (entity.getNumberOfLeave() > 20

				&& !entity.getStatus().equalsIgnoreCase("Terminated")) {

			entity.setStatus("Terminated");

			employeerepo.save(entity);

		}

		ResponseDto response = UtilityMapper.usingResponseDto(entity);

		// response.setId(entity.getId());

		return response;

	}

	@Transactional
	@Override
	public HttpStatus deleteTerminatedEmployee() {

		int value = employeerepo.deleteDummy();

		if (value == 0) {

			return HttpStatus.NOT_FOUND;
		}

		return HttpStatus.OK;
	}

	public static ResponseDto usingResponseDto(EmployeeEntity entity) {

		LocalDate currentDate = LocalDate.now();

		long overallDays = ChronoUnit.DAYS.between(entity.getDateofjoining(), currentDate);

		int daysworked = (int) overallDays - entity.getNumberOfLeave();

		ResponseDto dto = ResponseDto.builder().id(entity.getId()).name(entity.getName()).status(entity.getStatus())
				.numberOfLeave(entity.getNumberOfLeave()).basicPay(entity.getSalary().getBasicPay())
				.daysWorked(daysworked).build();

		return dto;

	}

	public static EmployeeEntity requestdaoToEntityBuilder(EmployeeSalaryDao dao) {

		EmployeeEntity employeeEntityBuild = EmployeeEntity.builder().name(dao.getName()).dob(dao.getDob())
				.dateofjoining(dao.getDateofjoining()).status(dao.getStatus()).numberOfLeave(dao.getNumberOfLeave())
				.salary(dao.getSalary()).build();

		return employeeEntityBuild;

	}

	// unused methods

	@Override
	public List<EmployeeEntity> terminatedEmployees(String status) {

		// List<EmployeeEntity> list = employeerepo.findByStatus(status);

		return null;
	}

	@Override
	public EmployeeSalaryDao createEmployee(EmployeeSalaryDao dao) {

		// from DAO to store entity and it return DAO

		EmployeeEntity employeeEntity = new EmployeeEntity();

		employeeEntity.setId(dao.getId());
		employeeEntity.setName(dao.getName());
		employeeEntity.setStatus(dao.getStatus());
		employeeEntity.setDob(dao.getDob());
		employeeEntity.setDateofjoining(dao.getDateofjoining());
		employeeEntity.setNumberOfLeave(dao.getNumberOfLeave());

		SalaryEntity salaryEntity = new SalaryEntity();
		salaryEntity.setSalaryid(dao.getSalary().getSalaryid());
		salaryEntity.setBasicPay(dao.getSalary().getBasicPay());
		salaryEntity.setHra(dao.getSalary().getHra());
		salaryEntity.setDa(dao.getSalary().getDa());
		salaryEntity.setMisc(dao.getSalary().getMisc());

		employeeEntity.setSalary(salaryEntity);

		salaryEntity.setEmployee(employeeEntity);

		employeerepo.save(employeeEntity);

		return dao;

	}

}

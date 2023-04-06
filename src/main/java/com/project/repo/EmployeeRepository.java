package com.project.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

	@Query(value = "SELECT * FROM employee_entity WHERE status = :status", nativeQuery = true)
	List<EmployeeEntity> findByEmploymentStatusByQuerySample(@Param("status") String status);

	@Modifying
	@Query(value = "delete from employee_entity " + "where status = 'LWD'", nativeQuery = true)
	int deleteDummy();

}

package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.SalaryEntity;

public interface SalaryRepository extends JpaRepository<SalaryEntity, Integer>{

}

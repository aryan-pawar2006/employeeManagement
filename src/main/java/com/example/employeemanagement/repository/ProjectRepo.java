package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<ProjectEntity,Integer> {
}

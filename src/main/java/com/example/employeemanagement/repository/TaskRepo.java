package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity,Integer> {
    List<TaskEntity> findByProject_Id(Integer projectId);

}

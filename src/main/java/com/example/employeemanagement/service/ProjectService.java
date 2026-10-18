package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.*;
import com.example.employeemanagement.entity.EmployeeEntity;
import com.example.employeemanagement.entity.ProjectEntity;
import com.example.employeemanagement.entity.TaskEntity;

import java.util.List;

public interface ProjectService {
    //include all the project services methods:
    public ProjectResponseDTO createProject(ProjectRequestDTO project);

    public TaskResponseDTO createTask(TaskRequestDTO task);

    public String deleteProject(Integer Id);

    public List<EmployeeResponseDTO> seeAllEmp(Integer projectId);

    public List<TaskResponseDTO> getAllTasks(Integer projectId);
}

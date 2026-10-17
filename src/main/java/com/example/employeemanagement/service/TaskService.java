package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.ProjectResponseDTO;
import com.example.employeemanagement.dto.TaskRequestDTO;
import com.example.employeemanagement.dto.TaskResponseDTO;
import com.example.employeemanagement.entity.ProjectEntity;
import com.example.employeemanagement.entity.TaskEntity;

public interface TaskService {
    //create method which is belongs to Task:
    public String assignTask(Integer employeeId,Integer TaskId);
    public TaskResponseDTO createTask(TaskRequestDTO task);
    public String deleteTask(Integer taskId);
    public ProjectResponseDTO getProject(Integer taskId);
}

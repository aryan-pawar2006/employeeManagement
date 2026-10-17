package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeRequestDTO;
import com.example.employeemanagement.dto.EmployeeResponseDTO;
import com.example.employeemanagement.dto.TaskResponseDTO;
import com.example.employeemanagement.entity.EmployeeEntity;
import com.example.employeemanagement.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    //here we need to add all the methods that we goon be used in our project
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employee);
    public EmployeeResponseDTO removeEmployee(Integer id);
    public EmployeeResponseDTO searchEmployee(Integer id);
    public List<EmployeeResponseDTO> getAllEmployee();
    public TaskResponseDTO getTask(Integer employeeId);
}

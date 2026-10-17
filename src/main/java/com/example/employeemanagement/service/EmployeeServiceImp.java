package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeRequestDTO;
import com.example.employeemanagement.dto.EmployeeResponseDTO;
import com.example.employeemanagement.dto.TaskRequestDTO;
import com.example.employeemanagement.dto.TaskResponseDTO;
import com.example.employeemanagement.entity.EmployeeEntity;
import com.example.employeemanagement.entity.TaskEntity;
import com.example.employeemanagement.exception.ResourceNotFound;
import com.example.employeemanagement.repository.EmployeeRepo;
import com.example.employeemanagement.repository.TaskRepo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Service
public class EmployeeServiceImp implements  EmployeeService{
    //create all thedependency that we have needed :
    EmployeeRepo employeeRepo;
    TaskRepo taskRepo;

    public EmployeeServiceImp(EmployeeRepo employeeRepo, TaskRepo taskRepo) {
        this.employeeRepo = employeeRepo;
        this.taskRepo = taskRepo;
    }

    @Override
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employee) {
        EmployeeEntity employee1=new EmployeeEntity();

        employee1.setName(employee.getName());
        employee1.setExperience(employee.getExperience());
        employee1.setSalary(employee.getSalary());
        employee1.setTaskId(employee.getTaskId());

        employeeRepo.save(employee1);

        EmployeeResponseDTO response=new EmployeeResponseDTO();

        response.setId(employee1.getId());
        response.setName(employee1.getName());
        response.setExperience(employee1.getExperience());
        response.setSalary(employee1.getSalary());
        response.setTaskId(employee1.getTaskId());


        return response;
    }

    @Override
    public EmployeeResponseDTO removeEmployee(Integer id) {
        EmployeeEntity empl=employeeRepo.findById(id)
                .orElseThrow(()->new ResourceNotFound("employee with the  given id not found!"));

        EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();

        responseDTO.setId(empl.getId());
        responseDTO.setName(empl.getName());
        responseDTO.setTaskId(empl.getTaskId());
        responseDTO.setExperience(empl.getExperience());
        responseDTO.setSalary(empl.getSalary());

        employeeRepo.deleteById(id);
        return responseDTO;
    }

    @Override
    public EmployeeResponseDTO searchEmployee(Integer id) {
        EmployeeEntity empl=employeeRepo.findById(id)
                .orElseThrow(()->new ResourceNotFound("employee does not exist!"));

        EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();

        responseDTO.setId(empl.getId());
        responseDTO.setName(empl.getName());
        responseDTO.setTaskId(empl.getTaskId());
        responseDTO.setExperience(empl.getExperience());
        responseDTO.setSalary(empl.getSalary());
        return responseDTO;
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployee() {
        List<EmployeeEntity> resList=employeeRepo.findAll();

        List<EmployeeResponseDTO> responseDTOList=new ArrayList<>();


        for (EmployeeEntity employeeEntity:resList) {
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();

            responseDTO.setId(employeeEntity.getId());
            responseDTO.setName(employeeEntity.getName());
            responseDTO.setTaskId(employeeEntity.getTaskId());
            responseDTO.setExperience(employeeEntity.getExperience());
            responseDTO.setSalary(employeeEntity.getSalary());

            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }

    @Override
    public TaskResponseDTO getTask(Integer employeeId) {
        EmployeeEntity employee=employeeRepo.findById(employeeId)
                .orElseThrow(()->new ResourceNotFound("employee does not exist!"));

        TaskResponseDTO responseDTO=new TaskResponseDTO();

        responseDTO.setId(employee.getId());
        TaskEntity task=taskRepo.getById(employee.getTaskId());
        responseDTO.setProjectId(task.getProjectId());
        responseDTO.setAssignedDate(task.getAssignedDate());
        responseDTO.setStatus(task.getStatus());
        return responseDTO;
    }

    //here now implement all the methods that you have created
}

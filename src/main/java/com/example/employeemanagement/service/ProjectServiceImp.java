package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.*;
import com.example.employeemanagement.entity.EmployeeEntity;
import com.example.employeemanagement.entity.ProjectEntity;
import com.example.employeemanagement.entity.TaskEntity;
import com.example.employeemanagement.exception.ResourceNotFound;
import com.example.employeemanagement.repository.EmployeeRepo;
import com.example.employeemanagement.repository.ProjectRepo;
import com.example.employeemanagement.repository.TaskRepo;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService{

    ProjectRepo projectRepo;
    TaskRepo taskRepo;
    EmployeeRepo employeeRepo;
    public ProjectServiceImp(ProjectRepo projectRepo,TaskRepo taskRepo,EmployeeRepo employeeRepo ) {
        this.projectRepo = projectRepo;
        this.taskRepo=taskRepo;
        this.employeeRepo=employeeRepo;
    }

    @Override
    public ProjectResponseDTO createProject(ProjectRequestDTO project)
    {
        ProjectEntity project1=new ProjectEntity();

        project1.setCreatedAt(project.getCreatedAt());
        project1.setDescription(project.getDescription());
        project1.setTitle(project.getTitle());

        projectRepo.save(project1);

        ProjectResponseDTO responseDTO=new ProjectResponseDTO();

        responseDTO.setId(project1.getId());
        responseDTO.setCreatedAt(project.getCreatedAt());
        responseDTO.setDescription(project.getDescription());
        responseDTO.setTitle(project.getTitle());

        return responseDTO;
    }

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO task) {
        TaskEntity task1=new TaskEntity();

        task1.setProjectId(task.getProjectId());
        task1.setAssignedDate(task.getAssignedDate());
        task1.setStatus(task.getStatus());

         taskRepo.save(task1);

         TaskResponseDTO responseDTO=new TaskResponseDTO();

         responseDTO.setProjectId(task1.getProjectId());
         responseDTO.setStatus(task1.getStatus());
         responseDTO.setId(task1.getId());
         responseDTO.setAssignedDate(task1.getAssignedDate());

         return responseDTO;
    }

    @Override
    public String deleteProject(Integer Id) {
        projectRepo.deleteById(Id);
        return "Done";
    }

    @Override
    public List<EmployeeResponseDTO> seeAllEmp(Integer projectId) {
        List<EmployeeEntity> employeeList=employeeRepo.findAll();

        employeeList.stream().filter(employeeEntity -> {
                    TaskEntity task = taskRepo.getById(employeeEntity.getId());
                    return task.getProjectId()==projectId;
                }
        );

      List<EmployeeResponseDTO> list=new ArrayList<>();

      for (EmployeeEntity employee:employeeList)
      {
          EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();
          responseDTO.setSalary(employee.getSalary());
          responseDTO.setName(employee.getName());
          responseDTO.setExperience(employee.getExperience());
          responseDTO.setId(employee.getId());
          responseDTO.setTaskId(employee.getTaskId());
          list.add(responseDTO);
      }
      return  list;
    }

    @Override
    public List<TaskResponseDTO> getAllTasks(Integer projectId) {
        List<TaskEntity> taskList=taskRepo.findAll();
        taskList.stream().filter(taskEntity ->
        {
            ProjectEntity project=projectRepo.findById(taskEntity.getId())
                    .orElseThrow(()->new ResourceNotFound("project does not found !"));
            return project.getId() == projectId;
        }).toList();

        List<TaskResponseDTO> responseDTOs=new ArrayList<>();

        for (TaskEntity task:taskList)
        {
            TaskResponseDTO responseDTO1=new TaskResponseDTO();

            responseDTO1.setId(task.getId());
            responseDTO1.setAssignedDate(task.getAssignedDate());
            responseDTO1.setProjectId(task.getProjectId());
            responseDTO1.setStatus(task.getStatus());

            responseDTOs.add(responseDTO1);
        }
        return responseDTOs;
    }
}

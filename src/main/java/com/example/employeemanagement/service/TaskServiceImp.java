package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.ProjectResponseDTO;
import com.example.employeemanagement.dto.TaskRequestDTO;
import com.example.employeemanagement.dto.TaskResponseDTO;
import com.example.employeemanagement.entity.EmployeeEntity;
import com.example.employeemanagement.entity.ProjectEntity;
import com.example.employeemanagement.entity.TaskEntity;
import com.example.employeemanagement.exception.ResourceNotFound;
import com.example.employeemanagement.repository.EmployeeRepo;
import com.example.employeemanagement.repository.ProjectRepo;
import com.example.employeemanagement.repository.TaskRepo;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImp implements TaskService{
    EmployeeRepo employeeRepo;
    TaskRepo taskRepo;
    ProjectRepo projectRepo;

    public TaskServiceImp(EmployeeRepo employeeRepo, TaskRepo taskRepo,ProjectRepo projectRepo) {
        this.employeeRepo = employeeRepo;
        this.taskRepo = taskRepo;
        this.projectRepo=projectRepo;
    }

    @Override
    public String assignTask(Integer employeeId, Integer TaskId) {
        EmployeeEntity employee=employeeRepo.findById(employeeId)
                        .orElseThrow(()->new ResourceNotFound("employee not found!"));

        employee.setTaskId(TaskId);
        return "Done";
    }

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO task) {

        // Check whether project is received
        if (task.getProjectId() == 0) {
            throw new IllegalArgumentException(
                    "Project is missing from request JSON"
            );
        }

        // Check whether project ID is received
        Integer projectId = task.getProjectId();

        if (projectId == 0) {
            throw new IllegalArgumentException(
                    "Project ID is missing. Send project.id in JSON"
            );
        }

        ProjectEntity project = projectRepo.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFound("Project not found: " + projectId)
                );

        task.setProjectId(project.getId());

        TaskEntity taskEntity=new TaskEntity();

        taskEntity.setStatus(task.getStatus());
        taskEntity.setAssignedDate(task.getAssignedDate());
        taskEntity.setProjectId(task.getProjectId());

        taskRepo.save(taskEntity);

        TaskResponseDTO responseDTO=new TaskResponseDTO();

        responseDTO.setStatus(taskEntity.getStatus());
        responseDTO.setId(taskEntity.getId());
        responseDTO.setAssignedDate(taskEntity.getAssignedDate());
        responseDTO.setProjectId(taskEntity.getProjectId());

        return responseDTO;
    }
    @Override
    public String deleteTask(Integer taskId) {
        taskRepo.deleteById(taskId);
        return "Done";
    }

    @Override
    public ProjectResponseDTO getProject(Integer taskId) {
        TaskEntity task=taskRepo.findById(taskId)
                .orElseThrow(()->new ResourceNotFound("task does not found!"));
        ProjectEntity project=projectRepo.getById(task.getProjectId());

        ProjectResponseDTO responseDTO=new ProjectResponseDTO();
        responseDTO.setId(project.getId());
        responseDTO.setTitle(project.getTitle());
        responseDTO.setDescription(project.getDescription());
        responseDTO.setCreatedAt(project.getCreatedAt());
        return responseDTO;
    }
}

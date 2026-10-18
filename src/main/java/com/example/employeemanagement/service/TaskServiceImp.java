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
        employeeRepo.save(employee);
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
        //find out the projet first with help of id:
        taskEntity.setProject(project);

        taskRepo.save(taskEntity);


        // for creating the response we are doing follwing things :
        TaskResponseDTO responseDTO=new TaskResponseDTO();

        responseDTO.setStatus(taskEntity.getStatus());
        responseDTO.setId(taskEntity.getId());
        responseDTO.setAssignedDate(taskEntity.getAssignedDate());
        //get project first:
        ProjectEntity project2=projectRepo.findById(task.getProjectId())
                .orElseThrow(()->new ResourceNotFound("project not found"));

        ProjectResponseDTO  responseDTO1=new ProjectResponseDTO();
        responseDTO1.setId(project2.getId());
        responseDTO1.setCreatedAt(project2.getCreatedAt());
        responseDTO1.setDescription(project2.getDescription());
        responseDTO1.setTitle(project2.getTitle());
        responseDTO.setProject(responseDTO1);
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

        ProjectResponseDTO responseDTO=new ProjectResponseDTO();
        responseDTO.setId(task.getProject().getId());
        responseDTO.setTitle(task.getProject().getTitle());
        responseDTO.setDescription(task.getProject().getDescription());
        responseDTO.setCreatedAt(task.getProject().getCreatedAt());
        return responseDTO;
    }
}

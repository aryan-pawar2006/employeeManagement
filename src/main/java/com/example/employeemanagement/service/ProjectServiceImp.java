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
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService {

    ProjectRepo projectRepo;
    TaskRepo taskRepo;
    EmployeeRepo employeeRepo;

    public ProjectServiceImp(ProjectRepo projectRepo, TaskRepo taskRepo, EmployeeRepo employeeRepo) {
        this.projectRepo = projectRepo;
        this.taskRepo = taskRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public ProjectResponseDTO createProject(ProjectRequestDTO project) {
        ProjectEntity project1 = new ProjectEntity();

        project1.setCreatedAt(project.getCreatedAt());
        project1.setDescription(project.getDescription());
        project1.setTitle(project.getTitle());

        projectRepo.save(project1);

        ProjectResponseDTO responseDTO = new ProjectResponseDTO();

        responseDTO.setId(project1.getId());
        responseDTO.setCreatedAt(project.getCreatedAt());
        responseDTO.setDescription(project.getDescription());
        responseDTO.setTitle(project.getTitle());

        return responseDTO;
    }

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO task) {
        TaskEntity task1 = new TaskEntity();

        ProjectEntity project = projectRepo.findById(task.getProjectId())
                .orElseThrow(() -> new ResourceNotFound("project does not found"));

        task1.setProject(project);
        task1.setAssignedDate(task.getAssignedDate());
        task1.setStatus(task.getStatus());

        taskRepo.save(task1);

        TaskResponseDTO responseDTO = new TaskResponseDTO();

        ProjectEntity project1 = projectRepo.findById(task1.getId())
                .orElseThrow(() -> new RuntimeException("project not found!"));
        ProjectResponseDTO responseDTO1 = new ProjectResponseDTO();
        responseDTO1.setId(project1.getId());
        responseDTO1.setTitle(project1.getTitle());
        responseDTO1.setDescription(project1.getDescription());
        responseDTO1.setCreatedAt(project1.getCreatedAt());


        responseDTO.setProject(responseDTO1);
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
        List<EmployeeEntity> employeeList = employeeRepo.findAll();

        employeeList.stream().filter(employeeEntity -> {
                    TaskEntity task = taskRepo.getById(employeeEntity.getId());
                    return task.getProject().getId() == projectId;
                }
        );

        List<EmployeeResponseDTO> list = new ArrayList<>();

        for (EmployeeEntity employee : employeeList) {
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            responseDTO.setSalary(employee.getSalary());
            responseDTO.setName(employee.getName());
            responseDTO.setExperience(employee.getExperience());
            responseDTO.setId(employee.getId());
            responseDTO.setTaskId(employee.getTaskId());
            list.add(responseDTO);
        }
        return list;
    }

    @Override
    public List<TaskResponseDTO> getAllTasks(Integer projectId) {

        ProjectEntity project = projectRepo.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFound("Project not found: " + projectId));

        List<TaskEntity> tasks = taskRepo.findByProject_Id(projectId);

        List<TaskResponseDTO> responses = new ArrayList<>();

        for (TaskEntity task : tasks) {

            TaskResponseDTO response = new TaskResponseDTO();
            response.setId(task.getId());
            response.setAssignedDate(task.getAssignedDate());
            response.setStatus(task.getStatus());

            ProjectEntity taskProject = task.getProject();

            if (taskProject == null) {
                throw new IllegalStateException(
                        "Task " + task.getId() + " has no associated project"
                );
            }

            ProjectResponseDTO projectDTO = new ProjectResponseDTO();
            projectDTO.setId(taskProject.getId());
            projectDTO.setTitle(taskProject.getTitle());
            projectDTO.setDescription(taskProject.getDescription());
            projectDTO.setCreatedAt(taskProject.getCreatedAt());

            response.setProject(projectDTO);
            responses.add(response);
        }

        return responses;
    }
}

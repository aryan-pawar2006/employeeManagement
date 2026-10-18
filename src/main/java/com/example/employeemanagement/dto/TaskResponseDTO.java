package com.example.employeemanagement.dto;

import com.example.employeemanagement.entity.ProjectEntity;

import java.time.LocalDateTime;

public class TaskResponseDTO {
    private Integer Id;
    private ProjectResponseDTO project;
    private LocalDateTime assignedDate;
    private String status;

    public ProjectResponseDTO getProject() {
        return project;
    }

    public Integer getId() {
        return Id;
    }

    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProject(ProjectResponseDTO project) {
        this.project = project;
    }
}

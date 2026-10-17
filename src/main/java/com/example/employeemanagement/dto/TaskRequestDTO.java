package com.example.employeemanagement.dto;

import com.example.employeemanagement.entity.ProjectEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TaskRequestDTO {
    @NotNull(message = "projectId cannot be null!")
    private Integer projectId;
    private LocalDateTime assignedDate;
    @NotNull(message = "status cannot be null!")
    private String status;


    public Integer getProjectId() {
        return projectId;
    }

    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}

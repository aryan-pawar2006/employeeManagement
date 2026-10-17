package com.example.employeemanagement.dto;

import com.example.employeemanagement.entity.ProjectEntity;

import java.time.LocalDateTime;

public class TaskResponseDTO {
    private Integer Id;
    private Integer projectId;
    private LocalDateTime assignedDate;
    private String status;

    public Integer getProjectId() {
        return projectId;
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

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}

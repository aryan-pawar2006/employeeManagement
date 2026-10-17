package com.example.employeemanagement.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

//this entity will  have all the task related information :
@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer projectId;
    private LocalDateTime assignedDate;
    private String status;

    public Integer getId() {
        return id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

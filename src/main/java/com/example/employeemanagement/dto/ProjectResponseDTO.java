package com.example.employeemanagement.dto;

import java.time.LocalDateTime;

public class ProjectResponseDTO {
    private Integer Id;
    private String title;
    private LocalDateTime createdAt;
    private String description;

    public Integer getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

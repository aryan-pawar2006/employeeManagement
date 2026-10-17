package com.example.employeemanagement.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ProjectRequestDTO {
    @NotBlank(message = "title field cannot be blank!")
    private String title;
    private LocalDateTime createdAt;
    @NotBlank(message = "description cannot be blank !")
    private String description;

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
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

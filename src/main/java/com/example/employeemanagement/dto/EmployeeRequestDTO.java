package com.example.employeemanagement.dto;

import com.example.employeemanagement.entity.TaskEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class EmployeeRequestDTO {
    //this will contains values for employe data request :
    @NotBlank(message = "name cannot be blank!")
    private String name;
    @NotNull(message = "taskId cannot be null!")
    private Integer taskId;
    @NotNull(message = "salary cannot be null!")
    @Positive
    private Double salary;
    @NotNull(message = "experience cannot be null!")
    @PositiveOrZero()
    private Double experience;


    public Integer getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public Double getExperience() {
        return experience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}

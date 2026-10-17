package com.example.employeemanagement.dto;

import com.example.employeemanagement.entity.TaskEntity;

public class EmployeeResponseDTO {
    //this will have response info:
    private Integer Id;
    private String name;
    private Integer taskId;
    private Double salary;
    private Double experience;


    public Integer getTaskId() {
        return taskId;
    }

    public Integer getId() {
        return Id;
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

    public void setId(Integer id) {
        Id = id;
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

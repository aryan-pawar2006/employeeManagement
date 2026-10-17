package com.example.employeemanagement.entity;
//this entity will include all the information of the employee :

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer  taskId;
    private Double salary;
    private Double experience;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public Double getSalary() {
        return salary;
    }

    public Double getExperience() {
        return experience;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIs() {
    }
}

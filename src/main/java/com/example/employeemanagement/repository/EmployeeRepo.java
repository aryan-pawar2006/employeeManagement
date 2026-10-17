package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
//this is employee repo which will andle the employee related data base operation .
public interface EmployeeRepo extends JpaRepository<EmployeeEntity,Integer> {

}

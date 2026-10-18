package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.*;
import com.example.employeemanagement.entity.EmployeeEntity;
import com.example.employeemanagement.entity.ProjectEntity;
import com.example.employeemanagement.entity.TaskEntity;
import com.example.employeemanagement.service.EmployeeService;
import com.example.employeemanagement.service.ProjectServiceImp;
import com.example.employeemanagement.service.TaskServiceImp;
import jakarta.validation.Valid;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//this class will actually handle all the http Requests:
@RestController
@RequestMapping("/employeeManagement")
public class EmployeeController {
    EmployeeService employeeService;
    ProjectServiceImp projectServiceImp;
    TaskServiceImp taskServiceImp;

    public EmployeeController(EmployeeService employeeService,ProjectServiceImp projectServiceImp,TaskServiceImp taskServiceImp) {
        this.employeeService = employeeService;
        this.projectServiceImp=projectServiceImp;
        this.taskServiceImp=taskServiceImp;
    }
    @PostMapping("/create")
    public EmployeeResponseDTO createEmployee(@Valid @RequestBody EmployeeRequestDTO employee)
    {
       return  employeeService.addEmployee(employee);
    }

    @GetMapping("/getEmployee/{Id}")
    public EmployeeResponseDTO getEmployee(@PathVariable Integer Id)
    {
        return employeeService.searchEmployee(Id);
    }

    @DeleteMapping("/deleteEmployee/{Id}")
    public String deleteEmployee(@PathVariable Integer Id)
    {
         employeeService.removeEmployee(Id);
         return "Done";
    }

    @GetMapping("/getAllEmployee")
    public List<EmployeeResponseDTO> getAllEmployee()
    {

        return employeeService.getAllEmployee();
    }

    @PostMapping("/createTask")
    public TaskResponseDTO createTask(@Valid @RequestBody TaskRequestDTO task )
    {
        return taskServiceImp.createTask(task);
    }

    @PutMapping("/assignTask/{employeeId}/{taskId}")
    public String assignTask(@PathVariable Integer employeeId,@PathVariable Integer taskId)
    {
        taskServiceImp.assignTask(employeeId,taskId);
        return "Done";
    }

    @PostMapping("/createProject")
    public ProjectResponseDTO createProject(@Valid @RequestBody ProjectRequestDTO project)
    {
        return projectServiceImp.createProject(project);
    }

    @GetMapping("/getTask/{employeeId}")
    public TaskResponseDTO getTask(@PathVariable Integer employeeId)
    {
        return employeeService.getTask(employeeId);
    }

    @DeleteMapping("/deleteProject/{projectId}")
    public String deleteProject(@PathVariable Integer projectId)
    {
        projectServiceImp.deleteProject(projectId);
        return "Done";
    }

    @GetMapping("/seeAllEmployee/{projectId}")
    public List<EmployeeResponseDTO> seeAllEmpl(@PathVariable Integer projectId)
    {
        return projectServiceImp.seeAllEmp(projectId);
    }

    @GetMapping("/getAllTasks/{projectId}")
    public List<TaskResponseDTO> getAllTasks(@PathVariable Integer projectId)
    {
        return projectServiceImp.getAllTasks(projectId);
    }

    @DeleteMapping("/deleteTask/{taskId}")
    public String deleteTask(@PathVariable Integer taskId)
    {
        taskServiceImp.deleteTask(taskId);
        return "Done";
    }

    @GetMapping("/getProject/{taskId}")
    public ProjectResponseDTO getProject(@PathVariable Integer taskId)
    {
        return taskServiceImp.getProject(taskId);
    }
 }

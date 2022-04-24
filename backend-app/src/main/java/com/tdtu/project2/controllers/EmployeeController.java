package com.tdtu.project2.controllers;

import com.tdtu.project2.models.Employee;
import com.tdtu.project2.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService emService) {
        this.service = emService;
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping(value = "/{username}/{password}")
    public Employee findEmployeeByUsernameAndPassword(@PathVariable String username,
            @PathVariable String password) {
        return service.findEmployeeByUsernameAndPassword(username, password);
    }

    @PostMapping(value = "/insert")
    public void insertEmployee(@RequestBody Employee employee) {
        service.insertEmployee(employee);
    }
    
    @GetMapping(value = "/getEmployee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }
}

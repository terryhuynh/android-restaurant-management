package com.tdtu.project2.services.impl;

import com.tdtu.project2.models.Employee;
import com.tdtu.project2.repositories.EmployeeRepository;
import com.tdtu.project2.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repository = repo;
    }

    @Override
    public Employee findEmployeeByUsernameAndPassword(String username, String password) {

        return this.repository.findEmployeeByUsernameAndPassword(username, password);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.repository.findAll();
    }

    @Override
    public void insertEmployee(Employee employee) {
        this.repository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return this.repository.getEmployeeById(id);
    }
}

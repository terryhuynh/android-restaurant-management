package com.tdtu.project2.services;

import java.util.List;
import com.tdtu.project2.models.Employee;

public interface EmployeeService {

    Employee findEmployeeByUsernameAndPassword(String username, String password);

    List<Employee> getAllEmployees();

    void insertEmployee(Employee employee);
    
    Employee getEmployeeById(Long id);
}

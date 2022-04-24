package com.tdtu.project2.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tdtu.project2.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select em from Employee em where em.username = ?1 and em.password = ?2")
    Employee findEmployeeByUsernameAndPassword(String username, String password);

    List<Employee> findAll();
    
    @Query("select em from Employee em where em.id = ?1")
    Employee getEmployeeById(Long id);
}

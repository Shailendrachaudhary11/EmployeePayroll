package com.example.EmployeePayRoll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.EmployeePayRoll.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

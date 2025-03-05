package com.example.EmployeePayRoll;

import com.example.EmployeePayRoll.model.Employee;
import com.example.EmployeePayRoll.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component  // Marks this as a startup component
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public DataLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) {
        // Adding sample employees
        employeeRepository.save(new Employee(null, "Riya", "riyabansal3365@gmail.com","CSE"));
        employeeRepository.save(new Employee(null, "Rashi", "rashiag456@gmail.com", "HR"));
    }
}
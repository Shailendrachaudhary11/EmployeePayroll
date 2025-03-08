package com.example.EmployeePayRoll.service;

import com.example.EmployeePayRoll.dto.EmployeeDTO;
import com.example.EmployeePayRoll.model.Employee;
import com.example.EmployeePayRoll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {

    private final List<Employee> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1); // Generates unique IDs

    // Add new employee
    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(idCounter.getAndIncrement(), employeeDTO.getName(), employeeDTO.getSalary());
        employeeList.add(employee);
        return employee;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : employeeList) {
            employeeDTOs.add(new EmployeeDTO(employee.getName(), employee.getSalary()));
        }
        return employeeDTOs;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> employee = employeeList.stream().filter(e -> e.getId().equals(id)).findFirst();
        return employee.map(e -> new EmployeeDTO(e.getName(), e.getSalary())).orElse(null);
    }

    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        for (Employee employee : employeeList) {
            if (employee.getId().equals(id)) {
                employee.setName(employeeDTO.getName());
                employee.setSalary(employeeDTO.getSalary());
                return employee;
            }
        }
        return null;
    }

    public boolean deleteEmployee(Long id) {
        return employeeList.removeIf(employee -> employee.getId().equals(id));
    }
}

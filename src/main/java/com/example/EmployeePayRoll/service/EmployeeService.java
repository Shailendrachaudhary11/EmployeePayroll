package com.example.EmployeePayRoll.service;

import com.example.EmployeePayRoll.dto.EmployeeDTO;
import com.example.EmployeePayRoll.model.Employee;
//import com.example.EmployeePayRoll.service.EmployeeService;
import com.example.EmployeePayRoll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class EmployeeService {

//    List<Employee> employeeList = new ArrayList<>();
//    private long nextId = 1;

    @Autowired
    EmployeeRepository repository;

    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll()
                .stream()
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        return repository.findById(id)
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()))
                .orElse(null);
    }

    public EmployeeDTO saveEmployee(@Valid EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSalary());
        Employee savedEmployee = repository.save(employee);
        return new EmployeeDTO(savedEmployee.getName(), savedEmployee.getSalary());
    }

    public EmployeeDTO updateEmployee(Long id, @Valid EmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployee = repository.findById(id);

        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            Employee updatedEmployee = repository.save(employee);
            return new EmployeeDTO(updatedEmployee.getName(), updatedEmployee.getSalary());
        }
        return null;
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
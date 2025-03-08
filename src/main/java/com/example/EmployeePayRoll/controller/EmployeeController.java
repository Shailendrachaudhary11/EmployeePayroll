package com.example.EmployeePayRoll.controller;

import com.example.EmployeePayRoll.dto.EmployeeDTO;
import com.example.EmployeePayRoll.model.Employee;
import com.example.EmployeePayRoll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.saveEmployee(employeeDTO);
    }

    @GetMapping
    public List<EmployeeDTO> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        return isDeleted ? "Employee deleted successfully!" : "Employee not found!";
    }
}

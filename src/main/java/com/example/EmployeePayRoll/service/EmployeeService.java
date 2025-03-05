package com.example.EmployeePayRoll.service;

import com.example.EmployeePayRoll.model.Employee;
import com.example.EmployeePayRoll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll(); }
    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null); }
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee); }
    public void deleteEmployee(Long id) {
        repository.deleteById(id); }
}
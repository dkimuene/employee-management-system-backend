package com.example.cruddemo.service;

import com.example.cruddemo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public List<Employee> findAll();
    public Optional<Employee> findById(int theId);
    public void save(Employee theEmployee);
    public void deleteById(int theId);
}

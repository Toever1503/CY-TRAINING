package com.service.impl;

import com.domain.Employee;
import com.repository.IRepository;
import com.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final IRepository employeeRepository;

    public EmployeeServiceImpl(IRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return (Employee) this.employeeRepository.findById(id);
    }
}

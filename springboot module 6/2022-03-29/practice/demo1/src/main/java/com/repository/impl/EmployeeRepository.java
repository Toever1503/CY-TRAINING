package com.repository.impl;

import com.domain.Employee;
import com.repository.IRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository implements IRepository<Employee, Long> {
    private static List<Employee> list = new ArrayList<Employee>();
    {
        list.add(new Employee(1l, "emp 1"));
        list.add(new Employee(2l, "emp 2"));
        list.add(new Employee(3l, "emp 3"));
        list.add(new Employee(4l, "emp 4"));
        list.add(new Employee(5l, "emp 5"));
        list.add(new Employee(6l, "emp 6"));
    }

    @Override
    public List<Employee> findAll() {
        return this.list;
    }

    @Override
    public Employee findById(Long id) {
        return this.list.stream().filter(emp -> emp.getId().equals(id)).findFirst().orElse(null);
    }
}

package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Page<Employee> listAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 6); // size are the numbers of how many to be shown on each site
        return employeeRepository.findAll(pageable);
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee get(int id) {
        return employeeRepository.findById(id).get();
    }


}

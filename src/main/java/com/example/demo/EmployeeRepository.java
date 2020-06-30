package com.example.demo;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {



}

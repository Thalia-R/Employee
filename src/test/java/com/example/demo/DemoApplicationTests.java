package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class DemoApplicationTests {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
    }


    @Test
    void shouldFindNumberOfEmployees() {
        assertEquals(3, employeeRepository.count());
    }


    @Test
    void shouldFindNumberOfUsers() {
        assertEquals(0, userRepository.count());
    }

}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/")
    public String viewAllEmployees(Model model) {

        return paginationForm(model, 1);
    }

    @GetMapping("/page/{pageNumber}")
    public String paginationForm(Model model, @PathVariable("pageNumber") int currentPage) {


        Page<Employee> page = employeeService.listAll(currentPage);
        long totalNumberOfEmployees = page.getTotalElements();
        int totalNumberOfPages = page.getTotalPages();

        List<Employee> employeeList = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalNumberOfEmployees", totalNumberOfEmployees);
        model.addAttribute("totalNumberOfPages", totalNumberOfPages);
        model.addAttribute("employeeList", employeeList);

        return "employees";
    }

    //  add employee from form
    @GetMapping("/add")
    public String EmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employee-form";
    }


    // delete employee from form data
    @PostMapping("/delete")
    public String deleteEmployee(Integer id) {
        return null;
    }


}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
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



    // edit employee
    @GetMapping("/edit/{id}")
    public String editEmployee(Model model, @PathVariable Integer id) {
        Employee employee = employeeRepository.findById(id).get();
        model.addAttribute("employee", employee);

        return "employee-form";
    }

    // delete employee from form data
    @RequestMapping("/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Integer id) {
        employeeService.deleteEmployeeById(id);

        return "redirect:/";
    }


    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "new-employee-form";
    }

    @PostMapping("/save")
    public String set(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/";
    }



}

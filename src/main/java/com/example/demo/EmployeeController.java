package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;



    @GetMapping("/employees")
    public String viewEmployees(Model model){

        List<Employee> emps = (List<Employee>) employeeRepository.findAll();
        model.addAttribute("emps", emps);

        return "employees";
    }

    //  add employee from form
    @GetMapping("/add")
    public String EmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employee-form";
    }

    // save employee from form data
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);

        return "employees";
    }

    // edit employee
    @GetMapping("/edit/{id}")
    public String editEmployee(Model model, @PathVariable Integer id) {
        Employee employee = employeeRepository.findById(id).get();
        model.addAttribute("employee", employee);

        return "employee-form";
    }



}

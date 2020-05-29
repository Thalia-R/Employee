package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> d1ee88c702e4dba7add8f128bb4dd35eb2b79e97
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


<<<<<<< HEAD
    // delete employee from form data
    @PostMapping("/delete")
    public String deleteEmployee(Integer id) {
        return null;
    }

=======
        return "redirect:/employees";
    }

    // edit employee
    @GetMapping("/edit/{id}")
    public String editEmployee(Model model, @PathVariable Integer id) {
        Employee employee = employeeRepository.findById(id).get();
        model.addAttribute("employee", employee);

        return "employee-form";
    }

    // delete employee from form data
    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable (value = "id") Integer id) {
        Employee emp = employeeRepository.findById(id).get();
        employeeRepository.delete(emp);

        return "redirect:/employees";
    }


>>>>>>> d1ee88c702e4dba7add8f128bb4dd35eb2b79e97

}

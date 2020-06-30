package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Base64;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FileRepository fileRepository;

    User user;


    @GetMapping("/employee")
    public String viewAllEmployees(Model model) {
        model.addAttribute("user", new User());
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
    @GetMapping("/edit-employee/{id}")
    public String editEmployee(Model model, @PathVariable Integer id) {
        Employee employee = employeeRepository.findById(id).get();

        model.addAttribute("employee", employee);

        return "edit-employee";
    }

    // View Employee
    @GetMapping("/employee-form/{id}")
    public String viewEmployee(Model model, @PathVariable Integer id) {
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

    @GetMapping("/documentation/{id}")
    public String viewAllFiles(Model model, @PathVariable Integer id) {
        Employee employee = employeeRepository.findById(id).get();
        model.addAttribute("employee", employee);

        return "documentation";
    }


    @PostMapping("/fileupload")
    public String uploadFiles(@RequestParam("file")MultipartFile file) {
       fileRepository.save(file);
        return "uploadSucess";
    }



    // creates a new employee
    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "new-employee-form";
    }

    // saves the new employee to database
    @PostMapping("/save")
    public String set(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/")
    public String loginPageDefault(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }


    // creates a new user


}

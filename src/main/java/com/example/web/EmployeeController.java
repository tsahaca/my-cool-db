package com.example.web;

import com.example.exception.RecordNotFoundException;
import com.example.model.EmployeeEntity;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("/list-employees")
    public String showUserList(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        return "list-employees";
    }

    @GetMapping("/edit")
    public String showAddEditForm(Model model) {
        model.addAttribute("employee", new EmployeeEntity());
        return "add-edit-employee";
    }

    @PostMapping("/createEmployee")
    public String addUser(EmployeeEntity employee, BindingResult result, Model model) throws RecordNotFoundException {
        if (result.hasErrors()) {
            return "add-edit-employee";
        }
        service.createOrUpdateEmployee(employee);
        return "redirect:/list-employees";
    }
}

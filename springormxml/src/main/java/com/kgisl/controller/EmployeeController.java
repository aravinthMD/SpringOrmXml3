package com.kgisl.controller;

import com.kgisl.model.Employee;
import com.kgisl.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class EmployeeController {
   
    @Autowired
    private EmployeeService employeeService;
 
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    @GetMapping
    public String listemployees(Model mod) {
        mod.addAttribute("employee", "new Employee()");
        mod.addAttribute("employeeList", employeeService.listEmployees());
        mod.addAttribute("sample","hellow");
        System.out.println("*********************");
        System.out.println("interior=="+employeeService.listEmployees());
        return "employee";
    }
 
    // Same method For both Add and Update Employee
    @RequestMapping(value = "/employee/add", method = RequestMethod.GET)
    public String addemployee(@ModelAttribute("employee") Employee employee) {
 
        if (employee.getEmployeeId()==null || employee.getEmployeeId() == 0) {
            // new employee, add it
            employeeService.addEmployee(employee);
        } else {
            // existing employee, call update
            employeeService.updateEmployee(employee);
        }
 
        return "redirect:/employees";
 
    }
 
    @RequestMapping("/employee/remove/{id}")
    public String removeemployee(@PathVariable("id=2") int id) {
 
        employeeService.removeEmployee(id);
        return "redirect:/employees";
    }
 
    @RequestMapping("/employee/edit/{id}")
    public String editemployee(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("employeeList", employeeService.listEmployees());
        return "employee";
    }
}
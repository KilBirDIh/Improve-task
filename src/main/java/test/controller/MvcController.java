package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import test.dao.IOperations;
import test.models.Department;
import test.models.Employee;
import test.models.Meeting;

import java.util.List;

@Controller
public class MvcController
{
    @Autowired
    IOperations<Employee> employeeService;
    @Autowired
    IOperations<Department> departmentService;
    @Autowired
    IOperations<Meeting> meetingService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(Model model) {


        List<Employee> employees = employeeService.findAll();
        List<Department> departments = departmentService.findAll();
        model.addAttribute("employeesList", employees);
        model.addAttribute("departmentList", departments);
        return "index";

    }
}

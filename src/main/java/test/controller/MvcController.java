package test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import test.models.Department;
import test.models.Employee;
import test.dao.GenericHibernateDao;
import test.dao.IGenericDao;

import java.util.List;

@Controller
public class MvcController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(Model model) {

        IGenericDao<Employee> employeeDao = new GenericHibernateDao<>();
        IGenericDao<Department> departmentDao = new GenericHibernateDao<>();

        List<Employee> employees = employeeDao.findAll();
        List<Department> departments = departmentDao.findAll();
        model.addAttribute("employeesList", employees);
        model.addAttribute("departmentList", departments);
        return "index";

    }
}

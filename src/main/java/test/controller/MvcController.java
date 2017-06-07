package test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import test.dao.IOperations;
import test.models.Department;
import test.models.Employee;
import test.models.Filter;
import test.models.Meeting;
import test.utils.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MvcController
{
    @Resource(name = "employeeService")
    private IOperations<Employee> employeeService;
    @Resource(name = "departmentService")
    private IOperations<Department> departmentService;
    @Resource(name = "meetingService")
    private IOperations<Meeting> meetingService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(Model model, @ModelAttribute("filter") Filter filter) {

        model.addAttribute("employeesList", employeeService.findAll());
        model.addAttribute("departmentList", departmentService.findAll());
        model.addAttribute("meetingList", meetingService.findAll());
        return "index";

    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filterMeetings(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "beginDate", required = false) String beginDate,
                                 @RequestParam(value = "endDate", required = false) String endDate,
                                 @RequestParam(value = "department", required = false) Integer departmentId,
                                 @RequestParam(value = "employee", required = false) Integer employeeId,
                                 @ModelAttribute("filter") Filter filter,
                                 Model model)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalTime midnight = LocalTime.MIDNIGHT;
        MeetingList meetings = new MeetingList(meetingService.findAll());
        IListDecorator decorator = new ListDecorator(meetings);
        IListDecorator byNameDecorator = new ByNameListDecorator(decorator, name);
        IListDecorator byEmployeeDecorator = new ByEmployeeListDecorator(byNameDecorator, employeeId);
        IListDecorator byDepartmentDecorator = new ByDepartmentListDecorator(byEmployeeDecorator, departmentId);
        IListDecorator byDateDecorator = new ByDateListDecorator(byDepartmentDecorator,
                LocalDateTime.of(LocalDate.parse(beginDate, formatter), midnight),
                LocalDateTime.of(LocalDate.parse(endDate, formatter), midnight));
        List<Employee> employees = employeeService.findAll();
        List<Department> departments = departmentService.findAll();
        model.addAttribute("employeesList", employees);
        model.addAttribute("departmentList", departments);
        model.addAttribute("meetingList", byDateDecorator.getList());
        return "index";
    }
}

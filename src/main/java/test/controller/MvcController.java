package test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import test.dao.IOperations;
import test.domain.Filter;
import test.domain.Meeting;
import test.models.Department;
import test.models.Employee;
import test.utils.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

@Controller
public class MvcController
{
    @Resource(name = "employeeService")
    private IOperations<Employee> employeeService;
    @Resource(name = "departmentService")
    private IOperations<Department> departmentService;
    @Resource(name = "meetingService")
    private IOperations<test.models.Meeting> meetingService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(Model model, @ModelAttribute("filter") Filter filter) {

        fillModel(model);
        model.addAttribute("meetingList", meetingService.findAll());
        return "index";

    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filterMeetings(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "beginDate", required = false) String beginDate,
                                 @RequestParam(value = "endDate", required = false) String endDate,
                                 @RequestParam(value = "department", required = false) Long departmentId,
                                 @RequestParam(value = "employee", required = false) Long employeeId,
                                 @ModelAttribute("filter") Filter filter,
                                 Model model)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDateTime beginDateTime;
        LocalDateTime endDateTime;
        if("".equals(beginDate)) beginDateTime = null;
        else beginDateTime = LocalDateTime.of(LocalDate.parse(beginDate, formatter), midnight);
        if("".equals(endDate)) endDateTime = null;
        else endDateTime = LocalDateTime.of(LocalDate.parse(endDate, formatter), midnight);
        MeetingList meetings = new MeetingList(meetingService.findAll());
        IListDecorator decorator = new ListDecorator(meetings);
        IListDecorator byNameDecorator = new ByNameListDecorator(decorator, name);
        IListDecorator byEmployeeDecorator = new ByEmployeeListDecorator(byNameDecorator, employeeId);
        IListDecorator byDepartmentDecorator = new ByDepartmentListDecorator(byEmployeeDecorator, departmentId);
        IListDecorator byDateDecorator = new ByDateListDecorator(byDepartmentDecorator,
               beginDateTime, endDateTime);
        fillModel(model);
        model.addAttribute("meetingList", byDateDecorator.getList());
        return "index";
    }

    @RequestMapping(value = "/meeting/create", method = RequestMethod.GET)
    public String createMeeting(@ModelAttribute Meeting meeting, Model model)
    {
        fillModel(model);
        return "meeting";
    }



    private void fillModel(Model model)
    {
        List<Employee> employees = employeeService.findAll();
        List<Department> departments = departmentService.findAll();
        model.addAttribute("employeesList", employees);
        model.addAttribute("departmentList", departments);
    }

    @RequestMapping(value = "/meeting/create", method = RequestMethod.POST)
    public String addMeeting(@ModelAttribute Meeting meeting, @ModelAttribute("filter") Filter filter, Model model)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        test.models.Meeting result = new test.models.Meeting();
        result.setSubject(meeting.getSubject());
        result.setMeetingDateTime(LocalDateTime.parse(meeting.getDatetime().replace('T', ' '), formatter));
        result.setResponsibleDepartment(departmentService.findOne(meeting.getDepartment()));
        result.setResponsibleEmployee(employeeService.findOne(meeting.getResponsibleEmployee()));
        meetingService.create(result);
        fillModel(model);
        model.addAttribute("meetingList", meetingService.findAll());
        return "index";
    }

    @RequestMapping(value = "/meeting/edit", method = RequestMethod.GET)
    public String editMeeting(@RequestParam(value = "id") Long id, @ModelAttribute("meeting") Meeting meeting, Model model)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        meeting.setMeetingId(id);
        test.models.Meeting dbMeeting = meetingService.findOne(id);
        meeting.setDatetime(dbMeeting.getMeetingDateTime().format(formatter).replace(' ', 'T'));
        meeting.setDepartment(dbMeeting.getResponsibleDepartment().getId());
        meeting.setResponsibleEmployee(dbMeeting.getResponsibleEmployee().getId());
        meeting.setSubject(dbMeeting.getSubject());
        model.addAttribute("membersList", dbMeeting.getMembersOfMeeting());
        fillModel(model);
        return "editmeeting";
    }

    @RequestMapping(value = "/meeting/edit", method = RequestMethod.POST)
    public String updateMeeting(@ModelAttribute("meeting") Meeting meeting, @ModelAttribute("filter") Filter filter, Model model)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        test.models.Meeting dbMeeting = meetingService.findOne(meeting.getMeetingId());
        dbMeeting.setMeetingDateTime(LocalDateTime.parse(meeting.getDatetime().replace('T', ' '), formatter));
        dbMeeting.setResponsibleDepartment(departmentService.findOne(meeting.getDepartment()));
        dbMeeting.setResponsibleEmployee(employeeService.findOne(meeting.getResponsibleEmployee()));
        dbMeeting.setSubject(meeting.getSubject());
        model.addAttribute("membersList", dbMeeting.getMembersOfMeeting());
        meetingService.update(dbMeeting);
        fillModel(model);
        model.addAttribute("meetingList", meetingService.findAll());
        return "index";
    }

    @RequestMapping(value = "/meeting/delete", method = RequestMethod.POST)
    public String deleteMember(@ModelAttribute("meeting") Meeting meeting, @RequestParam("id") Long id, Model model)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        test.models.Meeting dbMeeting = meetingService.findOne(meeting.getMeetingId());
        dbMeeting.setMeetingDateTime(LocalDateTime.parse(meeting.getDatetime().replace('T', ' '), formatter));
        dbMeeting.setResponsibleDepartment(departmentService.findOne(meeting.getDepartment()));
        dbMeeting.setResponsibleEmployee(employeeService.findOne(meeting.getResponsibleEmployee()));
        dbMeeting.setSubject(meeting.getSubject());
        Iterator<Employee> iterator = dbMeeting.getMembersOfMeeting().iterator();
        while (iterator.hasNext())
        {
            if (iterator.next().getId().equals(id))
            {
                iterator.remove();
                break;
            }
        }
        meetingService.update(dbMeeting);
        model.addAttribute("membersList", dbMeeting.getMembersOfMeeting());
        meetingService.update(dbMeeting);
        fillModel(model);
        model.addAttribute("meetingList", meetingService.findAll());
        return "editmeeting";
    }
}

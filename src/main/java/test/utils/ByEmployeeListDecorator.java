package test.utils;

import org.springframework.beans.factory.annotation.Autowired;
import test.dao.IOperations;
import test.models.Employee;
import test.models.Meeting;

import java.util.List;
import java.util.ListIterator;

public class ByEmployeeListDecorator extends ListDecorator
{
    private Integer id;
    @Autowired
    private IOperations<Employee> employeeService;

    public ByEmployeeListDecorator(IListDecorator listDecorator, Integer id)
    {
        super(listDecorator);
        this.id = id;
    }

    @Override
    public List<Meeting> getList()
    {
        List<Meeting> result = super.getList();
        if(id != 0)
        {
            Employee employee = employeeService.findOne(id);
            ListIterator<Meeting> iterator = result.listIterator();
            while (iterator.hasNext())
            {
                if (!iterator.next().getMembersOfMeeting().contains(employee)) iterator.remove();
            }
        }
        return result;
    }
}
